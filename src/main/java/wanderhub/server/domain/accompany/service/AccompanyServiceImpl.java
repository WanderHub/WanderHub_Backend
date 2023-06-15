package wanderhub.server.domain.accompany.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.domain.accompany.entity.Accompany;
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
    public void createAccompany(Accompany accompany) {
        accompany.setOpenStatus(true);
        accompanyRepository.save(accompany);
    }

    @Override
    public List<Accompany> findAll() {
        return accompanyRepository.findAll();
    }

    @Override
    public Optional<Accompany> findById(Long id) {
        return accompanyRepository.findById(id);
    }

    @Override
    public List<Accompany> findByLocal(String accompanyLocal) {
        return accompanyRepository.findByAccompanyLocal(accompanyLocal);
    }

    @Override
    public List<Accompany> findByDate(String accompanyDate) {
        return accompanyRepository.findByAccompanyDate(LocalDate.parse(accompanyDate));
    }

    @Override
    public List<Accompany> findByLocalAndDate(String accompanyLocal, String accompanyDate) {
        return accompanyRepository.findByAccompanyLocalAndAccompanyDate(accompanyLocal, LocalDate.parse(accompanyDate));
    }

    @Override
    public void deleteAccompany(Long id) {
        Accompany entity = accompanyRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        accompanyRepository.delete(entity);
    }






}
