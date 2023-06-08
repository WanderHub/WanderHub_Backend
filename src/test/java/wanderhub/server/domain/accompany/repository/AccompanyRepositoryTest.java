package wanderhub.server.domain.accompany.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wanderhub.server.domain.accompany.entity.Accompany;

import java.time.LocalDate;
import java.util.Date;
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
        Accompany accompany = new Accompany(null, 4L, "hi1", "서울", LocalDate.parse("2023-06-05"), 3, "제목", "내용", true);
        repo.save(accompany);
        System.out.println("accompanyDate = " + accompany.getAccompanyDate());
//        LocalDate d = LocalDate.parse("2023-05-01");
//        String s = "2023-05-02";
//        System.out.println(Integer.parseInt(s.substring(0,4)));
//        System.out.println(d.getYear());
//        System.out.println(Integer.parseInt(s.substring(5,7)));
//        System.out.println(d.getMonthValue());
        assertThat(accompany.getAccompanyTitle()).isEqualTo("제목");
    }

    @Test
    public void findAll() {
        Accompany accompany = new Accompany(null, 4L, "hi2", "대전", LocalDate.parse("2023-06-07"), 4, "제목2", "내용2", true);
        repo.save(accompany);

        List<Accompany> list = repo.findAll();
        Accompany accompanies = list.get(0);
        assertThat(accompanies.getAccompanyTitle()).isEqualTo("제목2");
    }

}
