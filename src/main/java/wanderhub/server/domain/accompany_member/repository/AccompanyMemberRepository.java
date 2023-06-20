package wanderhub.server.domain.accompany_member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany_member.entity.AccompanyMember;
import wanderhub.server.domain.member.entity.Member;

import java.util.List;

public interface AccompanyMemberRepository extends JpaRepository<AccompanyMember, Long> {

    List<AccompanyMember> findByAccompany(Accompany accompany);
    List<AccompanyMember> findByMember(Member member);

}
