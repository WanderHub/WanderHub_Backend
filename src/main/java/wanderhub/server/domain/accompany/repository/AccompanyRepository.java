package wanderhub.server.domain.accompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.util.List;
import java.util.Optional;

public interface AccompanyRepository extends JpaRepository<Accompany, Long> {

    List<Accompany> findAll();
    Optional<Accompany> findById(Long id);


}
