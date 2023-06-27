//package wanderhub.server.domain.accompany.mapper;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
//import wanderhub.server.domain.accompany.entity.Accompany;
//import wanderhub.server.domain.accompany.repository.AccompanyRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@DataJpaTest
//public class AccompanyMapperTest {
//
//    private final AccompanyRepository repo;
//
//    @Autowired
//    public AccompanyMapperTest(AccompanyRepository repo) {
//        this.repo = repo;
//    }
//
//    @Test
//    public void create() {
//        Accompany accompany = Accompany.builder()
//                        .nickname("nickname")
//                        .accompanyLocal("서울")
//                        .accompanyDate(LocalDate.parse("2023-06-01"))
//                        .maxNum(3)
//                        .accompanyTitle("제목")
//                        .accompanyContent("내용")
//                        .openStatus(true)
//                        .build();
//        repo.save(accompany);
//        AccompanyResponseDto responseDto = AccompanyMapper.INSTANCE.toDto(accompany);
//        Assertions.assertThat(responseDto.getNickname()).isEqualTo(accompany.getNickname());
//    }
//
//    @Test
//    public void findAll() {
//        Accompany accompany1 = Accompany.builder()
//                .nickname("nickname")
//                .accompanyLocal("서울")
//                .accompanyDate(LocalDate.parse("2023-06-01"))
//                .maxNum(3)
//                .accompanyTitle("제목")
//                .accompanyContent("내용")
//                .openStatus(true)
//                .build();
//        Accompany accompany2 = Accompany.builder()
//                .nickname("nickname2")
//                .accompanyLocal("제주도")
//                .accompanyDate(LocalDate.parse("2023-07-01"))
//                .maxNum(6)
//                .accompanyTitle("제목2")
//                .accompanyContent("내용2")
//                .openStatus(true)
//                .build();
//        repo.save(accompany1);
//        repo.save(accompany2);
//
//        List<Accompany> entityList = repo.findAll();
//        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
//        Assertions.assertThat(dtoList.get(0).getNickname()).isEqualTo(accompany1.getNickname());
//    }
//}
