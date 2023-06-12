package wanderhub.server.global.test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class HomeTestController {
    @Value("${spring.security.oauth2.client.registration.kakao.clientId}")
    private String kid;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String rediUri;

    @GetMapping("/")
    public String home(Model m) {
        log.info("kid = {}", kid);
        log.info("rediUri = {}", rediUri);
        m.addAttribute("client_id", kid);
        m.addAttribute("redirect_uri", rediUri);

        return "index";
    }

    @GetMapping("/receive-token")
    public ResponseEntity tokenTest(@RequestParam("access_token")String accessToken, @RequestParam("refresh_token")String refreshToken) {
        String tokens =  String.format("accessToken = %s     %n%n%n%n%n                  refreshToken = %s",accessToken,refreshToken) ;
        return new ResponseEntity(tokens, HttpStatus.CREATED);
    }
}
