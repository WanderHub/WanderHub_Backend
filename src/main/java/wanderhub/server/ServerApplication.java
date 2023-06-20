package wanderhub.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.service.MemberService;


@EnableJpaAuditing // JPA의 Auditing 기능 활성화
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

}
