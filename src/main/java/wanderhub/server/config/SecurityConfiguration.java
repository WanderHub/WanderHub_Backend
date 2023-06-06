package wanderhub.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // 구성정보 클래스
public class SecurityConfiguration {

    @Bean   // 스프링에서 관리하는 빈으로 설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 스프링 시큐리티 FilterChain 구성 // HttpSecurity를 통해서 HTTP요청에 대한 보안 설정을 구ㅅㅇ한다.
        http
                .formLogin().disable()  // formLogin 비활성화 // 우리 프로젝트에 자체 회원가입은 후순위
                .httpBasic().disable()  // request전송마다 Username/Password 정보를 Header에 실어서 인증하는 방식 // 사용 안 하므로 disable
                .oauth2Login(withDefaults());   //  OAuth2Login 인증 활성화

//        http
//                .authorizeRequests() // 접근 허용 request 설정해야함.

        return http.build(); // SecurityFilterChain 구성
    }
}