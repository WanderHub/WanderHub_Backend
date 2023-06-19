package wanderhub.server.domain.member.service;

import lombok.extern.slf4j.Slf4j;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final CustomBeanUtils<Member> customBeanUtils;

    // DI 생성자.
    public MemberService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils, CustomBeanUtils<Member> customBeanUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
        this.customBeanUtils = customBeanUtils;
    }

    public Member createMember(Member member) {
        member.setMemberStatus(MemberStatus.ACTIVE);                    // 멤버 활동 중 변경
        member.setRoles(authorityUtils.createRoles(member.getEmail())); // 멤버의 이메일로 권한 생성
        return memberRepository.save(member);   // 멤버를 DB에 저장한다.
    }

    // 멤버 수정 메서드
    public Member updateMember(Member member,Member updateMember) { // 원래있던 member , 수정할 정보를 가진 updateMember
        verificationActiveMember(member);   // 일단 휴면상태인지 검증
        verificatioinNickName(member, updateMember);    // 닉네임 검증
        return customBeanUtils.copyNonNullProoerties(updateMember, member); // update의 정보가 member로 저장된다.
    }

    // 멤버를 이메일로 찾는다.
        // 없으면 예외 던진다.
    public Member findMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


    // 멤버를 기본키로 찾는다.
        // 없으면 예외를 던진다.
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new CustomLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    // 회원가입시 이메일로 멤버 찾는 용도 사용 // OAuth2MemberSuccessHandler에서
    public Optional<Member> findByEmail(String email) {
        // 이메일로 멤버를 찾아온다.
        return memberRepository.findByEmail(email);
    }

    // 닉네임 검증 메서드
    public void verificatioinNickName(Member srcMember, Member updateMember) {
        if(srcMember.getNewbie()) { // 뉴비라면,
            verificationNewbie(updateMember);   // 뉴비검증 닉네임 정보 없으면 예외
            // 뉴비검증 통과시 닉네임 변경될 것이기때문에,
            // 신규회원여부를 false로 변경하여 기존 멤버라고 한다.
            srcMember.setNewbie(false);
        } else {
            verificationNotNewbie(updateMember);    // 기존 멤버에 닉네임 변경시도가 있으면 예외 발생
        }

    }

    // 뉴비는 닉네임을 변경해야하는데, 변경이 없다면, 변경하라고 예외발생시켜야함.
    public void verificationNewbie(Member updateMember) {
        // 뉴비인데, updateMember에도 닉네임정보가 없다면, 닉네임 변경을 해달라는 예외를 던진다.
        if(updateMember.getNickName()==null) {
            throw new CustomLogicException(ExceptionCode.NICKNAME_REQUIRED);
        }
    }

    // 기존 회원은 닉네임을 변경하려고할 때 예외를 발생시킨다.
    public void verificationNotNewbie(Member updateMember) {
        if(updateMember.getNickName()!=null) { // 기존멤버가 닉네임을 변경하려고하면, 예외 발생
            throw new CustomLogicException(ExceptionCode.NICKNAME_NOT_UPDATE);
        }
    }

    // 닉네임없거나 휴면상태인 회원이 서비스를 시작하려할 때, 검증 메서드
    public void verificationMember(Member member) {
        if(member.getNewbie() && member.getNickName()==null) {          // 닉네임이 없는 사람인지 검증
            throw new CustomLogicException(ExceptionCode.NICKNAME_REQUIRED);
        }
        verificationActiveMember(member);   // 휴면상태 검증
    }

    public void verificationMemberByEmail(String email) {
        verificationMember(findMember(email));  // 이메일로 검증하게하기 // select한번 '덜' 날리기 위해..
    }

    public void verificationActiveMember(Member member) {
        if(member.getMemberStatus()==MemberStatus.HUMAN) {              // 휴면상태인지 확인
            throw new CustomLogicException(ExceptionCode.MEMBER_ALREADY_HUMAN);
        }
    }
    
    // 회원탈퇴 => 휴면상태
    public void quitMember(String email) {
        verificationMemberByEmail(email);
        Member member = findMember(email);
        member.setMemberStatus(MemberStatus.HUMAN);
    }

    // 멤버조회
    public Member getMember(String email) {
        verificationMemberByEmail(email);
        return findMember(email);// 멤버있는지 확인
    }

}
