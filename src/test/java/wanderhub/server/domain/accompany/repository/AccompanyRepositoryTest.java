//package wanderhub.server.domain.accompany.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import wanderhub.server.domain.accompany.entity.Accompany;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//public class AccompanyRepositoryTest {
//
//    private final AccompanyRepository repo;
//
//    @Autowired
//    public AccompanyRepositoryTest(AccompanyRepository repo) {
//        this.repo = repo;
//    }
//
//    @Test
//    public void save() {
//        Accompany accompany = Accompany.builder()
//                .nickname("nickname")
//                .accompanyLocal("서울")
//                .accompanyDate(LocalDate.parse("2023-06-01"))
//                .maxNum(3)
//                .accompanyTitle("제목")
//                .accompanyContent("내용")
//                .openStatus(true)
//                .build();
//        repo.save(accompany);
//        assertThat(accompany.getAccompanyTitle()).isEqualTo("제목");
//    }
//
//    @Test
//    public void findAll() {
//        Accompany accompany = Accompany.builder()
//                .nickname("nickname")
//                .accompanyLocal("서울")
//                .accompanyDate(LocalDate.parse("2023-06-01"))
//                .maxNum(3)
//                .accompanyTitle("제목")
//                .accompanyContent("내용")
//                .openStatus(true)
//                .build();
//        repo.save(accompany);
//
//        List<Accompany> list = repo.findAll();
//        Accompany accompanies = list.get(0);
//        assertThat(accompanies.getAccompanyTitle()).isEqualTo("제목");
//    }
//
//}

