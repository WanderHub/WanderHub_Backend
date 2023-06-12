package wanderhub.server.global.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeTestController {
    @Value("${spring.security.oauth2.client.registration.kakao.clientId}")
    private String kid;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String rediUri;

    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("client_id", kid);
        m.addAttribute("redirect_uri", rediUri);

        return "index";
    }
}
