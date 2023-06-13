package wanderhub.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderhub.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
