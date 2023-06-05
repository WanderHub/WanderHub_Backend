package wanderhub.server.domain.accompany.repository;

import wanderhub.server.domain.accompany.entity.Accompany;

import java.util.List;
import java.util.Optional;

public interface AccompanyRepository2 {

    Accompany save(Accompany accompany);
    Optional<Accompany> findById(Long id);
    Optional<Accompany> findByName(String name);
    List<Accompany> findAll();

}
