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
        log.info("member = {}", member.getId());
        log.info("member = {}", member.getNickName());
        log.info("member = {}", member.getName());
        log.info("member = {}", member.getRoles());
        log.info("updateMember = {}",updateMember.getNickName());
        log.info("updateMember = {}",updateMember.getName());
        log.info("updateMember = {}",updateMember.getLocal());
        verificatioinNickName(member, updateMember);    // 닉네임 검증
        log.info("닉네임 검증!!!!!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
        log.info("닉네임 검증!!!!!!!!!!!!완료!!!!!!!!");
           // updateMember정보를 원래 member로
        return customBeanUtils.copyNonNullProoerties(updateMember, member);
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


    // 회원가입시 이메일로 멤버 찾는 용도 사용
    public Optional<Member> findByEmail(String email) {
        // 이메일로 멤버를 찾아온다.
        return memberRepository.findByEmail(email);
    }

    // 닉네임 검증 메서드
    public void verificatioinNickName(Member srcMember, Member updateMember) {
        // 기존 멤버 닉네임은 없고, update멤버에 닉네임이 있으면 닉네임 변경이므로, joinon(가입상태)를 true로 바꾼다.
        if(srcMember.getNickName()==null && updateMember.getNickName()!=null) {
            srcMember.setJoinOn(true);
            log.info("닉변 가능!!");
            log.info("닉변 가능!!");
            log.info("닉변 가능!!");
            log.info("닉변 가능!!");
            log.info("닉변 가능!!");
            log.info("닉변 가능!!");
        }
        // 기존멤버 닉네임이 없고, updateMember에도 닉네임정보가 없다면, 닉네임 변경을 해달라는 예외를 던진다.
        if(srcMember.getNickName()==null && updateMember.getNickName()==null) {
            throw new CustomLogicException(ExceptionCode.NICKNAME_REQUIRED);
        }
        // member의 joinOn이 true이고, update멤버에 nickname 정보가 있다면, 닉네임은 변경하지 못 한다는 예외를 발생시킨다.
        if(srcMember.getJoinOn()==true && updateMember.getJoinOn()!=null) {
            throw new CustomLogicException(ExceptionCode.NICKNAME_NOT_UPDATE);
        }
    }

    public boolean writeMemberStatus(Member member) {
        member.setMemberStatus(MemberStatus.HUMAN);
        return member.getMemberStatus()==MemberStatus.HUMAN;
    }
}
