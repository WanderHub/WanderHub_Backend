package wanderhub.server.domain.accompany.service;

import org.springframework.data.domain.Page;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface AccompanyService {

    Accompany createAccompany(Accompany accompany, String userEmail);
    List<Accompany> findAll();
//    List<Accompany> findAll(Pageable pageable);
//    Page<Accompany> findAll(Pageable pageable);
    Optional<Accompany> findById(Long id);
    List<Accompany> findByLocal(String accompanyLocal);
//    List<Accompany> findByLocal(String accompanyLocal, Pageable pageable);
    List<Accompany> findByDate(String accompanyDate);
    List<Accompany> findByLocalAndDate(String accompanyLocal, String accompanyDate);

    void deleteAccompany(Long id, String userEmail);
}
