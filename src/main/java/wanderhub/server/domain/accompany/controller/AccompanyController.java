package wanderhub.server.domain.accompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.service.AccompanyService;
import wanderhub.server.domain.accompany.service.AccompanyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accompany")
public class AccompanyController {

    private AccompanyService accompanyService;

    @Autowired
    public AccompanyController(AccompanyServiceImpl accompanyService) {
        this.accompanyService = accompanyService;
    }

    @GetMapping("/")
    public List<Accompany> all() {
        return accompanyService.findAll();
    }

    @GetMapping(value="/{id}")
    public Optional<Accompany> findOne(@PathVariable Long id) {
        return accompanyService.findById(id);
    }

//    @GetMapping("/{id}")
//    public Accompany getAccompanyByAccompanyId(@PathVariable("id") Long id) {
//        return accompanyService.findById(id);
//    }

    @PostMapping("/create")
    public Accompany create(@RequestBody Accompany accompany) {
        return accompanyService.createAccompany(accompany);
    }


}
