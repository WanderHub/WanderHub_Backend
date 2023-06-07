package wanderhub.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // 구성정보 클래스
public class SecurityConfiguration {

    @Bean   // 스프링에서 관리하는 빈으로 설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 스프링 시큐리티 FilterChain 구성 // HttpSecurity를 통해서 HTTP요청에 대한 보안 설정을 구성한다.
        http
                .headers().frameOptions().sameOrigin()  // 동일한 Origin(schema+hostname+port(생략가능))로부터 오는 접근만 허용
                .and()
                .csrf().disable()       // 세션을 사용하지 않기 때문에 비활성화
                .cors(withDefaults())   // corsConfigurationSource 이름으로 등록된 Bean을 이용 / 해당 Bean을 제공함으로써 CorsFilter 적용
                .formLogin().disable()  // formLogin 비활성화 // 우리 프로젝트에 자체 회원가입은 후순위
                .httpBasic().disable()  // request전송마다 Username/Password 정보를 Header에 실어서 인증하는 방식 // 사용 안 하므로 disable
                .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll()   // 인증된 요청에 대해서만 접근을 허용한다.
                )
                .oauth2Login(withDefaults());           // OAuth2 로그인 인증 활성화

        return http.build(); // SecurityFilterChain 구성
    }


    // CorsConfigurationSource Bean 생성 // CORS 정책설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));                              // 스크립트기반의 HTTP 통신 허용 // 운영서버에맞게 커스터마이징
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));  // 지정한 HTTP Method에 대한 통신 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();   // CorsConfigurationSource를 구현한 클래스
        source.registerCorsConfiguration("/**", configuration);                    // 모든 URL에 configuration에서 설정한 정책 적용
        return source;
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        var clientRegistration = clientRegistration();

        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    @Value("${spring.security.oauth2.client.registration.google.clientId}")  // (1)
    private Optional<String> clientId;

    @Value("${spring.security.oauth2.client.registration.google.clientSecret}") // (2)
    private Optional<String> clientSecret;


    @Value("${spring.security.oauth2.client.registration.kakao.clientId}")  // (1)
    private Optional<String> kclientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") // (2)
    private Optional<String> kclientSecret;

    @PostConstruct
    private ClientRegistration clientRegistration() {
        System.out.println("clientId = " + clientId.isPresent());
        System.out.println("clientSecret = " + clientSecret.isPresent());
        System.out.println("kclientId = " + kclientId.isPresent());
        System.out.println("kclientSecret = " + kclientSecret.isPresent());

        return CommonOAuth2Provider
                .GOOGLE
                .getBuilder("google")
                .clientId(clientId.get())
                .clientSecret(clientSecret.get())
                .build();
    }


}
