package wanderhub.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public CustomBeanUtils<Member> customBeanUtils;

    @Override
    public Member createMember(Member member) {

        member.setMemberStatus(MemberStatus.ACTIVE);                    // 멤버 활동 중 변경
        member.setRoles(authorityUtils.createRoles(member.getEmail())); // 멤버의 이메일로 권한 생성
        log.info("role = {}", member.getRoles());
        return memberRepository.save(member);   // 멤버를 DB에 저장한다.
    }


    // 멤버 수정 메서드
    @Override
    public Member updateMember(Member member,Member updateMember) { // 원래있던 member , 수정할 정보를 가진 updateMember
        // 다른유저와의 닉네임 검증
        // updateMember에 닉네임 정보가 있는지 확인한다.
            // 닉네임이 있다면 유저를 찾고,
                // 없다면, 통과하여 닉네임이 변경되도록 한다.
                // 만약 유저가 있다면, 닉네임을 변경할 유저가 맞는지 확인한다.
                    // 만약 다른 유저라면 '이미 있는 닉네임이 있다고 예외를 처리한다.

        // 닉네임을 변경하려는 시도가 있는지에 대한 검증
        if(!(member.getNickName()==updateMember.getNickName())) { // 닉네임이 다르면 예외 처리
            throw new CustomLogicException(ExceptionCode.DISPLAYNAME_NOT_UPDATE);
        }
        return customBeanUtils.copyNonNullProoerties(member, updateMember);
    }

    // 멤버를 이메일로 찾는다.
        // 없으면 예외 던진다.
    @Override
    public Member findMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


    // 멤버를 기본키로 찾는다.
        // 없으면 예외를 던진다.
    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


    // 회원가입시 이메일로 멤버 찾는 용도 사용
    @Override
    public Optional<Member> findByEmail(String email) {
        // 이메일로 멤버를 찾아온다.
        return memberRepository.findByEmail(email);
    }


    @Override
    public boolean writeMemberStatus(Member member) {
        member.setMemberStatus(MemberStatus.HUMAN);
        return member.getMemberStatus()==MemberStatus.HUMAN;
    }
}
