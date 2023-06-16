package wanderhub.server.domain.member.service;


import wanderhub.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberService {

    Member createMember(Member member); // 멤버를 생성한다.
    Member updateMember(Member findMember,Member updateMember);  // 멤버 정보를 수정한다.
    Member findMember(String email);   // 멤버를 이메일로 찾는다.
    Member findMember(Long id);        // 멤버를 기본키로 찾는다.
    Optional<Member> findByEmail(String email); // 멤버를 이메일을 통해 찾고, 있는지 없는지 검증해주어야 한다.

    boolean writeMemberStatus(Member member);  // 멤버가 탈퇴시도를 하면, 멤버의 상태를 휴면으로 바꾼다.

}
