package wanderhub.server.domain.accompany.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AccompanyServiceTest {

    @MockBean
    private final AccompanyService accompanyService;

    @Autowired
    public AccompanyServiceTest(AccompanyService accompanyService) {
        this.accompanyService = accompanyService;
    }

    @Test
    void createAccompany() {
        Accompany accompany = new Accompany(1L, 4L, "hi2", "대전", LocalDate.parse("2023-06-05"), 4, "제목2", "내용2", true);
        AccompanyDto dto = AccompanyMapper.INSTANCE.toRequestDto(accompany);
        System.out.println("dto = " + dto);
        accompanyService.createAccompany(dto);
        //System.out.println("res = " + res);

        //미결!!!!!!!!!!!!
        Optional<AccompanyResponseDto> byId = accompanyService.findById(1L);
        System.out.println("byId = " + byId);

        assertThat(dto.getWriterName()).isEqualTo("hi2");
    }


}
