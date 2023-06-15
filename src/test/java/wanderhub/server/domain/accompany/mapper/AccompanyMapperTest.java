package wanderhub.server.domain.accompany.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class AccompanyMapperTest {

    private final AccompanyRepository repo;

    @Autowired
    public AccompanyMapperTest(AccompanyRepository repo) {
        this.repo = repo;
    }

    @Test
    public void create() {
        Accompany accompany = new Accompany(null, 4L, "hi1", "서울", LocalDate.parse("2023-06-01"), 3, "제목", "내용", true);
        repo.save(accompany);
        AccompanyResponseDto accompanyDto = AccompanyMapper.INSTANCE.toDto(accompany);
    }

    @Test
    public void findAll() {
        //더미데이터 저장
        Accompany accompany1 = new Accompany(null, 5L, "user5", "제주", LocalDate.parse("2023-06-05"), 2, "제목1", "내용1", true);
        repo.save(accompany1);
        Accompany accompany2 = new Accompany(null, 6L, "user6", "서울", LocalDate.parse("2023-06-07"), 4, "제목2", "내용2", true);
        repo.save(accompany2);

        List<Accompany> entityList = repo.findAll();
        List<AccompanyResponseDto> dtoList = AccompanyMapper.INSTANCE.toDtoList(entityList);
        System.out.println("dtoList = " + dtoList);
    }


}
