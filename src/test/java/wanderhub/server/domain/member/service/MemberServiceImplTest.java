package wanderhub.server.domain.member.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.global.exception.CustomLogicException;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    final String EMAIL = "SsangSoo@gmail.com";


    @DisplayName("멤버가 생성된다.")
    @Test
    void createTest() {
        // given    // 멤버가 생성된 후,
        Member testMember = new Member(EMAIL);

        // when     // DB에 멤버를 넣는다.
        Member createdMember = memberService.createMember(testMember);

        // than
        assertEquals(createdMember.getEmail(), testMember.getEmail());
        assertEquals(createdMember.getRoles(), testMember.getRoles());
    }

    @DisplayName("멤버를 찾는다.")
    @Test
    void findTest() {
        // given
        Member member = new Member(EMAIL);    // 멤버를 생성한다.
        Member createdMember = memberService.createMember(member);

        // when                               // 멤버를 찾아온다.
        Member findMemberbyEmail = memberService.findMember(createdMember.getEmail());
        Member findMember = memberService.findMember(createdMember.getId());

        // than                             // 생성한 Member와 찾는 Member가 동일한 사용자인지 확인한다.
        assertEquals(createdMember, findMember);
        assertEquals(findMember, findMemberbyEmail);
        assertEquals(createdMember, findMemberbyEmail);


    }


    @DisplayName("멤버가 생성된 후, 닉네임을 재설정하려고 하면 예외처리를 한다.")
    @Test
    void DisplayNameUpdateExceptionTest() {
        // given
        Member member = Member.builder()
                                .email(EMAIL)
                                .nickName("쌩수")
                                .build();

        memberService.createMember(member);     // 닉네임을 가진 유저가 DB에 저장된다. // 닉네임 변경예외를 처리하기 위한 테스트라 그냥 생성하면서 DB에 저장하도록한다.

        Member patch = Member.builder()          // patchDto 정보로 사용자 정보를 수정하려고 할때,
                .name("이름")
                .nickName("쌩수 아닌 다른 닉네임")
                .local("부산")
                .build();
        // when // than // 닉네임을 재설정 할 수 없기 때문에 예외가 발생한다.
        Assertions.assertThrows(CustomLogicException.class, () -> {
            memberService.updateMember(member, patch);
        });

    }

    //////////////////////////////////////////////////////////////////////////////////////
//
//    @DisplayName("회원의 닉네임이 설정된 후엔 다시 재설정이 불가능하다.")
//    @Test
//    void test() {
//        // given
//        Member member1 = Member.builder()
//                .email(EMAIL)
//                .nickName("쌩수")
//                .build();
//
//        memberService.createMember(member1);
//
//        Member patch = Member.builder()
//                .name("이름")
//                .nickName("쌩수가 아님")
//                .local("부산")
//                .build();
//        // when
//        memberService.updateMember(member)
//
//        // than
//
//    }





}