package wanderhub.server.domain.member.service;


import wanderhub.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberService {

    Member createMember(Member member);
    Member patchMember(Member findMember,Member updateMember);
    Member findMember(Member member);
    Optional<Member> findByEmail(String email);


}
