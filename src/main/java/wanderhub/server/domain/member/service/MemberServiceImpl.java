package wanderhub.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.auth.utils.CustomAuthorityUtils;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.entity.MemberStatus;
import wanderhub.server.domain.member.repository.MemberRepository;
import wanderhub.server.global.exception.CustomLogicException;
import wanderhub.server.global.exception.ExceptionCode;
import wanderhub.server.global.utils.CustomBeanUtils;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public CustomBeanUtils<Member> customBeanUtils;


    public Member createMember(Member member) {
        // member가 이미 존재하는지 확인한다.
            // 존재하면 예외가 발생될 것이다.
        verificationMember(member);

        // 'verificationMember(member)' 메서드에 통과되었단 것은 member가 없다는 의미
        member.setMemberStatus(MemberStatus.ACTIVE);                    // 멤버 활동 중 변경
        member.setRoles(authorityUtils.createRoles(member.getEmail())); // 멤버의 이메일로 권한 생성
        return memberRepository.save(member);   // 멤버를 DB에 저장한다.
    }

    public Member patchMember(Member findMember,Member updateMember) {
        return customBeanUtils.copyNonNullProoerties(findMember, updateMember);
    }


    public Member findMember(Member member) {
        return findByEmail(member.getEmail()).get();
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    // 멤버가 이미 있는지 확인하는 메서드
        // 멤버가 있으면 예외 처리
    void verificationMember(Member member) {
        // 멤버를 Repository로부터 찾는다.
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        // 멤버가 있을 경우, 이미 존재하는 회원으로 예외처리를 한다.
        findMember.ifPresent(existsMember -> {
            throw new CustomLogicException(ExceptionCode.MEMBER_EXISTS);
        });
    }

}
