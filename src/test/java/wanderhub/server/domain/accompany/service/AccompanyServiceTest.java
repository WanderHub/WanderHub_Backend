package wanderhub.server.domain.accompany.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccompanyServiceTest {

    private AccompanyService accompanyService;
    private AccompanyRepository accompanyRepository;

    @Autowired
    public AccompanyServiceTest(AccompanyService accompanyService, AccompanyRepository accompanyRepository) {
        this.accompanyService = accompanyService;
        this.accompanyRepository = accompanyRepository;
    }

    @Test
    void createAccompany() {
        Accompany accompany = new Accompany(null, 4L, "hi2", "대전", 4, "제목2", "내용2", true);
        accompanyService.createAccompany(accompany);

        Accompany res = accompanyService.findById(accompany.getId()).get();
        assertThat(accompany.getWriterName()).isEqualTo(res.getWriterName());
    }


}
