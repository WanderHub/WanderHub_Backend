package wanderhub.server.domain.accompany.service;

import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccompanyService {

    AccompanyResponseDto createAccompany(AccompanyDto accompanyDto);
//    List<AccompanyDto> findAllByManual(); //수동방법
//    List<AccompanyDto> findAllByModelMapper(); //ModelMapper
    List<AccompanyResponseDto> findAll(); //mapStruct
    Optional<AccompanyResponseDto> findById(Long id);
    List<AccompanyResponseDto> findByLocal(String accompanyLocal);
    List<AccompanyResponseDto> findByDate(String accompanyDate);
    List<AccompanyResponseDto> findByLocalAndDate(String accompanyLocal, String accompanyDate);
    List<AccompanyResponseDto> findByMemberId(Long memberId);

    void deleteAccompany(Long id);
}
