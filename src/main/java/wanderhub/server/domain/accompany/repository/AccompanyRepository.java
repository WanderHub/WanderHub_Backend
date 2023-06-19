package wanderhub.server.domain.accompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccompanyRepository extends JpaRepository<Accompany, Long> {

    List<Accompany> findByAccompanyLocal(String local);
    List<Accompany> findByAccompanyDate(LocalDate date);
    List<Accompany> findByAccompanyLocalAndAccompanyDate(String local, LocalDate date);

}