package wanderhub.server.domain.accompany.service;

import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccompanyService {

    void createAccompany(Accompany accompany);
    List<Accompany> findAll();
    Optional<Accompany> findById(Long id);
    List<Accompany> findByLocal(String accompanyLocal);
    List<Accompany> findByDate(String accompanyDate);
    List<Accompany> findByLocalAndDate(String accompanyLocal, String accompanyDate);

    void deleteAccompany(Long id);
}
