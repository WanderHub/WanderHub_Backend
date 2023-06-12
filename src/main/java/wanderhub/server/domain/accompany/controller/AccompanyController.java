package wanderhub.server.domain.accompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
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
    public AccompanyResponseDto create(@RequestBody AccompanyDto accompanyDto) {
        return accompanyService.createAccompany(accompanyDto);
    }

    //전체 조회
    @GetMapping("/")
    public List<AccompanyResponseDto> findAll() {
        return accompanyService.findAll();
    }

    //accompanyId로 조회
    @GetMapping("/{id}")
    public Optional<AccompanyResponseDto> findById(@PathVariable Long id) {
        return accompanyService.findById(id);
    }

    //지역 별 조회
    @GetMapping("/bylocal/{local}")
    public List<AccompanyResponseDto> findByLocal(@PathVariable String local) {
        return accompanyService.findByLocal(local);
    }

    //일
    @GetMapping("/bydate/{date}")
    public List<AccompanyResponseDto> findByDate(@PathVariable String date) {
        return accompanyService.findByDate(date);
    }

    //지역->날짜로 조회
    @GetMapping("/bylocalanddate/{local}/{date}")
    public List<AccompanyResponseDto> findByLocalAndDate(@PathVariable String local, @PathVariable String date) {
        return accompanyService.findByLocalAndDate(local, date);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccompany(@PathVariable Long id) {
        accompanyService.deleteAccompany(id);
    }

}
