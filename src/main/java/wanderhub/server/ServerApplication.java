package wanderhub.server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ServerApplication {

    @Value("${profile}")
    @Getter
    static String profile;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        log.info("profile = {}", ServerApplication.profile);
    }

}
