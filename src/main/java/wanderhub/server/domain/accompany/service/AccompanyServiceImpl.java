package wanderhub.server.domain.accompany.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccompanyServiceImpl implements AccompanyService {

    private final AccompanyRepository accompanyRepository;

    @Override
    public AccompanyResponseDto createAccompany(AccompanyDto accompanyDto) {
        //dto to entity
        Accompany entity = AccompanyMapper.INSTANCE.toEntity(accompanyDto);

        //entity save
        accompanyRepository.save(entity);

        //entity to dto
        AccompanyResponseDto dto =AccompanyMapper.INSTANCE.toDto(entity);
        return dto;
    }

    //mapStruct
    @Override
    public List<AccompanyResponseDto> findAll() {
        List<Accompany> entityList = accompanyRepository.findAll();
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtoList;
    }

    @Override
    public Optional<AccompanyResponseDto> findById(Long id) {
        Accompany entity = accompanyRepository.findById(id)
                .orElseThrow(NullPointerException::new); //Optional에 담긴 값이 없다면 throw 던져줌=에러 발생 (Exception 커스텀 가능)

        AccompanyResponseDto dto = AccompanyMapper.INSTANCE.toDto(entity);
        Optional<AccompanyResponseDto> dto2 = Optional.of(dto);
        return dto2;
    }

    @Override
    public List<AccompanyResponseDto> findByLocal(String accompanyLocal) {
        List<Accompany> entityList = accompanyRepository.findByAccompanyLocal(accompanyLocal);
        List<AccompanyResponseDto> dtolist = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtolist;
    }

    @Override
    public List<AccompanyResponseDto> findByDate(String accompanyDate) {
        List<Accompany> entityList = accompanyRepository.findByAccompanyDate(LocalDate.parse(accompanyDate));
        List<AccompanyResponseDto> dtolist = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtolist;
    }

    @Override
    public List<AccompanyResponseDto> findByLocalAndDate(String accompanyLocal, String accompanyDate) {
        List<Accompany> entityList = accompanyRepository.findByAccompanyLocalAndAccompanyDate(accompanyLocal, LocalDate.parse(accompanyDate));
        List<AccompanyResponseDto> dtolist = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtolist;
    }

    @Override
    public List<AccompanyResponseDto> findByMemberId(Long memberId) {
        List<Accompany> entityList = accompanyRepository.findByMemberId(memberId);
        List<AccompanyResponseDto> dtolist = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtolist;
    }

    @Override
    public void deleteAccompany(Long id) {
        Accompany entity = accompanyRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        accompanyRepository.delete(entity);
    }






}
