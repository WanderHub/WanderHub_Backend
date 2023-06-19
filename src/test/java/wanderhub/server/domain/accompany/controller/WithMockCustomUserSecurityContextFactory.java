package wanderhub.server.domain.accompany.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.service.MemberService;

import java.util.Arrays;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    private final MemberService memberService;

    public WithMockCustomUserSecurityContextFactory(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        String value = annotation.value();
        Member principal = memberService.findMember(value);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, Arrays.asList(new SimpleGrantedAuthority(principal.getRoles().get(0))));

        context.setAuthentication(auth);
        return context;
    }
}
