package wanderhub.server.domain.accompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.service.AccompanyService;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.entity.MemberStatus;
import wanderhub.server.domain.member.service.MemberService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accompany")
@RequiredArgsConstructor
public class AccompanyController {

    private final AccompanyService accompanyService;
    private final MemberService memberService;

    //생성
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AccompanyDto accompanyDto) {
        Member m = new Member(1L, "name", "22@gmail.com", "nickname","img","local", MemberStatus.ACTIVE,null,null);
        memberService.createMember(m);

        Accompany entity = AccompanyMapper.INSTANCE.toEntity(accompanyDto);
        accompanyService.createAccompany(entity);
    }

    //전체 조회 (경로 임시)
    @GetMapping("")
    public ResponseEntity<List<AccompanyResponseDto>> findAll() {
        List<Accompany> entityList = accompanyService.findAll();
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return ResponseEntity.ok(dtoList);
    }

    //accompanyId로 조회
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccompanyResponseDto>> findById(@PathVariable Long id) {
        Accompany entity = accompanyService.findById(id)
                .orElseThrow(NullPointerException::new); //Optional에 담긴 값이 없다면 throw 던져줌=에러 발생 (Exception 커스텀 가능)
        AccompanyResponseDto dto = AccompanyMapper.INSTANCE.toDto(entity);

        return ResponseEntity.ok(Optional.of(dto));
    }

    //지역 별 조회
    @GetMapping("/bylocal")
    public ResponseEntity<List<AccompanyResponseDto>> findByLocal(@RequestParam(value = "accompanyLocal") String local) {
        List<Accompany> entityList = accompanyService.findByLocal(local);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return ResponseEntity.ok(dtoList);
    }

    //일
    @GetMapping("/bydate")
    public ResponseEntity<List<AccompanyResponseDto>> findByDate(@RequestParam(value = "accompanyDate") String date) {
        List<Accompany> entityList = accompanyService.findByDate(date);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/bylocalanddate")
    public ResponseEntity<List<AccompanyResponseDto>> findByLocalAndDate(@RequestParam(value = "accompanyLocal") String local, @RequestParam(value = "accompanyDate") String date) {
        List<Accompany> entityList = accompanyService.findByLocalAndDate(local, date);
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        return ResponseEntity.ok(dtoList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccompany(@PathVariable Long id) {
        accompanyService.deleteAccompany(id);
    }

}

//package wanderhub.server.domain.accompany.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import wanderhub.server.domain.accompany.dto.AccompanyDto;
//import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
//import wanderhub.server.domain.accompany.entity.Accompany;
//import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
//import wanderhub.server.domain.accompany.service.AccompanyService;
//import wanderhub.server.domain.member.entity.Member;
//import wanderhub.server.domain.member.service.MemberService;
//
//import java.security.Principal;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/accompany")
//@RequiredArgsConstructor
//@Slf4j
//public class AccompanyController {
//
//    private final AccompanyService accompanyService;
//    private AccompanyMapper accompanyMapper;
//    private final MemberService memberService;
//
//    //생성
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity create(Principal principal, @RequestBody AccompanyDto accompanyDto) {
//        String nickName = memberService.findMember(accompanyDto.getMemberId()).getNickName();
//
//        Accompany entity = accompanyMapper.INSTANCE.toEntity(accompanyDto);
//        entity.setNickname(nickName);
//        log.info("entity = {}" , entity);
//        Accompany accompany = accompanyService.createAccompany(entity);
//        log.info("accompany = {}", accompany);
//        AccompanyResponseDto accompanyResponseDto = accompanyMapper.INSTANCE.toDto(accompany);
//
//        return new ResponseEntity(accompanyResponseDto, HttpStatus.CREATED);
//    }
//
//    //전체 조회
//    @GetMapping("/")
//    public List<AccompanyResponseDto> findAll() {
//        List<Accompany> entityList = accompanyService.findAll();
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        return dtoList;
//    }
//
//    //accompanyId로 조회
//    @GetMapping("/{id}")
//    public Optional<AccompanyResponseDto> findById(@PathVariable Long id) {
//        Accompany entity = accompanyService.findById(id)
//                .orElseThrow(NullPointerException::new); //Optional에 담긴 값이 없다면 throw 던져줌=에러 발생 (Exception 커스텀 가능)
//        AccompanyResponseDto dto = AccompanyMapper.INSTANCE.toDto(entity);
//
//        return Optional.of(dto);
//    }
//
//    //지역 별 조회
//    @GetMapping("/bylocal/{local}")
//    public List<AccompanyResponseDto> findByLocal(@PathVariable String local) {
//        List<Accompany> entityList = accompanyService.findByLocal(local);
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        return dtoList;
//    }
//
//    //일
//    @GetMapping("/bydate/{date}")
//    public List<AccompanyResponseDto> findByDate(@PathVariable String date) {
//        List<Accompany> entityList = accompanyService.findByDate(date);
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        return dtoList;
//    }
//
//    //지역->날짜로 조회
////    @GetMapping("/bylocalanddate/{local}/{date}")
////    public List<AccompanyResponseDto> findByLocalAndDate(@PathVariable String local, @PathVariable String date) {
////        List<Accompany> entityList = accompanyService.findByLocalAndDate(local, date);
////        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
////        return dtoList;
////    }
//    @GetMapping("/bylocalanddate2")
//    public List<AccompanyResponseDto> findByLocalAndDate2(@RequestParam(value = "accompanyLocal") String local, @RequestParam(value = "accompanyDate") String date) {
//        List<Accompany> entityList = accompanyService.findByLocalAndDate(local, date);
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        return dtoList;
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAccompany(@PathVariable Long id) {
//        accompanyService.deleteAccompany(id);
//    }
//
//}
