package wanderhub.server.domain.accompany.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AccompanyRepositoryTest {

    private final AccompanyRepository repo;

    @Autowired
    public AccompanyRepositoryTest(AccompanyRepository repo) {
        this.repo = repo;
    }

    @Test
    public void save() {
        Accompany accompany = new Accompany(null, 4L, "hi1", "서울", 3, "제목", "내용", true);
        repo.save(accompany);
        assertThat(accompany.getAccompanyTitle()).isEqualTo("제목");
    }

    @Test
    public void findAll() {
        Accompany accompany = new Accompany(null, 4L, "hi2", "대전", 4, "제목2", "내용2", true);
        repo.save(accompany);

        List<Accompany> list = repo.findAll();

        Accompany accompanies = list.get(0);
        assertThat(accompanies.getAccompanyTitle()).isEqualTo("제목2");
    }

}
