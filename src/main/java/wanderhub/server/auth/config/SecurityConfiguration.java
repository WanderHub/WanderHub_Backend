package wanderhub.server.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import wanderhub.server.auth.jwt.JwtVerificationFilter;
import wanderhub.server.auth.handler.MemberAccessDeniedHandler;
import wanderhub.server.auth.handler.MemberAuthenticationEntryPoint;
import wanderhub.server.auth.jwt.JwtTokenizer;
import wanderhub.server.auth.handler.OAuth2MemberSuccessHandler;
import wanderhub.server.auth.oauth.CustomOAuth2MemberService;
import wanderhub.server.auth.utils.CustomAuthorityUtils;
import wanderhub.server.domain.member.service.MemberService;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // 구성정보 클래스
@EnableWebSecurity(debug = true)  // Spring Security 활성화
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;
    private final CustomOAuth2MemberService customOAuth2MemberService;


    @Bean   // 스프링에서 관리하는 빈으로 설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 스프링 시큐리티 FilterChain 구성 // HttpSecurity를 통해서 HTTP요청에 대한 보안 설정을 구성한다.
        http
                .headers().frameOptions().sameOrigin()  // 동일한 Origin(schema+hostname+port(생략가능))로부터 오는 접근만 허용
                .and()
                .csrf().disable()       // 세션을 사용하지 않기 때문에 비활성화
                .cors(withDefaults())   // corsConfigurationSource 이름으로 등록된 Bean을 이용 / 해당 Bean을 제공함으로써 CorsFilter 적용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않기 때문에, 세션정책을 아예 만들지 않게 설정한다.
                .and()
                .formLogin().disable()  // formLogin 비활성화 // 우리 프로젝트에 자체 회원가입은 후순위
                .httpBasic().disable()  // request전송마다 Username/Password 정보를 Header에 실어서 인증하는 방식 // 사용 안 하므로 disable
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authorize -> authorize   // url authorization 전체추가.
                        .anyRequest().permitAll()
                )
                .oauth2Login()  // OAuth2 로그인 인증 활성화
                .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService)
                )   // 소셜 로그인 성공한 이후에 이뤄질 Handler
                .userInfoEndpoint()
                .userService(customOAuth2MemberService);


        return http.build(); // SecurityFilterChain 구성
    }


    // CorsConfigurationSource Bean 생성 // CORS 정책설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));                              // 스크립트기반의 HTTP 통신 허용 // 운영서버에맞게 커스터마이징
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));  // 지정한 HTTP Method에 대한 통신 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();   // CorsConfigurationSource를 구현한 클래스

        source.registerCorsConfiguration("/**", configuration);                    // 모든 URL에 configuration에서 설정한 정책 적용
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            builder.addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }
}
