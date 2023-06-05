package wanderhub.server.domain.accompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.service.AccompanyService;
import wanderhub.server.domain.accompany.service.AccompanyServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accompany")
@RequiredArgsConstructor
public class AccompanyController {

    private AccompanyService accompanyService;

    @Autowired
    public AccompanyController(AccompanyServiceImpl accompanyService) {
        this.accompanyService = accompanyService;
    }

    @PostMapping("/create")
    public Accompany create(@RequestBody Accompany accompany) {
        return accompanyService.createAccompany(accompany);
    }

    @GetMapping("/")
    public List<Accompany> findAll() {
        return accompanyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Accompany> findById(@PathVariable Long id) {
        return accompanyService.findById(id);
    }



}
