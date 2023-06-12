package wanderhub.server.domain.accompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.service.AccompanyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accompany")
@RequiredArgsConstructor
public class AccompanyController {

    private final AccompanyService accompanyService;

    //생성
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AccompanyDto accompanyDto) {
        Accompany entity = AccompanyMapper.INSTANCE.toEntity(accompanyDto);
        accompanyService.createAccompany(entity);
    }

    //전체 조회
    @GetMapping("/")
    public List<AccompanyResponseDto> findAll() {
        List<Accompany> entityList = accompanyService.findAll();
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtoList;
    }

    //accompanyId로 조회
    @GetMapping("/{id}")
    public Optional<AccompanyResponseDto> findById(@PathVariable Long id) {
        Accompany entity = accompanyService.findById(id)
                .orElseThrow(NullPointerException::new); //Optional에 담긴 값이 없다면 throw 던져줌=에러 발생 (Exception 커스텀 가능)
        AccompanyResponseDto dto = AccompanyMapper.INSTANCE.toDto(entity);

        return Optional.of(dto);
    }

    //지역 별 조회
    @GetMapping("/bylocal/{local}")
    public List<AccompanyResponseDto> findByLocal(@PathVariable String local) {
        List<Accompany> entityList = accompanyService.findByLocal(local);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtoList;
    }

    //일
    @GetMapping("/bydate/{date}")
    public List<AccompanyResponseDto> findByDate(@PathVariable String date) {
        List<Accompany> entityList = accompanyService.findByDate(date);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtoList;
    }

    //지역->날짜로 조회
//    @GetMapping("/bylocalanddate/{local}/{date}")
//    public List<AccompanyResponseDto> findByLocalAndDate(@PathVariable String local, @PathVariable String date) {
//        List<Accompany> entityList = accompanyService.findByLocalAndDate(local, date);
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        return dtoList;
//    }
    @GetMapping("/bylocalanddate2")
    public List<AccompanyResponseDto> findByLocalAndDate2(@RequestParam(value = "accompanyLocal") String local, @RequestParam(value = "accompanyDate") String date) {
        List<Accompany> entityList = accompanyService.findByLocalAndDate(local, date);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return dtoList;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccompany(@PathVariable Long id) {
        accompanyService.deleteAccompany(id);
    }

}
