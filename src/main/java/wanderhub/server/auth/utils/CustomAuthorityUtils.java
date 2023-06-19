package wanderhub.server.auth.utils;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> ef77ccb16cabb4fbcd56646755dfaf3b782869dc
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

<<<<<<< HEAD
@Slf4j
=======
>>>>>>> ef77ccb16cabb4fbcd56646755dfaf3b782869dc
@Component
public class CustomAuthorityUtils {
    @Value("${admin.email}")
    private Set<String> admins; // admin 이메일 리스트

<<<<<<< HEAD

=======
>>>>>>> ef77ccb16cabb4fbcd56646755dfaf3b782869dc
    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    // DB에 저장된 Role을 기반으로 권한 정보 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    public List<String> createRoles(String email) {
<<<<<<< HEAD
        log.info("adminArray = {}",admins);
=======
>>>>>>> ef77ccb16cabb4fbcd56646755dfaf3b782869dc
        if (admins.contains(email)) {
            return ADMIN_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }
}