package wanderhub.server.domain.accompany.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.time.LocalDate;
import java.util.Date;

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
        Accompany accompany = new Accompany(1L, 4L, "hi2", "대전", LocalDate.parse("2023-06-05"), 4, "제목2", "내용2", true);
        AccompanyDto dto = AccompanyMapper.INSTANCE.toRequestDto(accompany);
        accompanyService.createAccompany(dto);

//        Accompany res = accompanyService.findById(accompany.getId()).get();
//        assertThat(accompany.getWriterName()).isEqualTo(res.getWriterName());
    }


}
