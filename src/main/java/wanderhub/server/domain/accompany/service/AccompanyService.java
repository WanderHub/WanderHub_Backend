package wanderhub.server.domain.accompany.service;

import wanderhub.server.domain.accompany.entity.Accompany;

import java.util.List;
import java.util.Optional;

public interface AccompanyService {

    Accompany createAccompany(Accompany accompany);
    List<Accompany> findAll();
    Optional<Accompany> findById(Long id);
//    Accompany findById(Long id);

}
