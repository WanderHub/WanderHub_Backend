package wanderhub.server.domain.accompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccompanyServiceImpl implements AccompanyService {

    private final AccompanyRepository accompanyRepository;

    @Autowired
    public AccompanyServiceImpl(AccompanyRepository accompanyRepository) {
        this.accompanyRepository = accompanyRepository;
    }

    @Override
    public Accompany createAccompany(Accompany accompany) {
        accompanyRepository.save(accompany);
        return accompany;
    }

    @Override
    public List<Accompany> findAll() {
        return accompanyRepository.findAll();
    }

    @Override
    public Optional<Accompany> findById(Long id) {
        return accompanyRepository.findById(id);
    }
//    @Override
//    public Accompany findById(Long id) {
//        return accompanyRepository.findById(id);
//    }
}
