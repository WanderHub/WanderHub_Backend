package wanderhub.server.domain.accompany.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.repository.AccompanyRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccompanyServiceTest {

    @Mock
    private AccompanyRepository accompanyRepository;

    @Test
    void createAccompany() {
        AccompanyService accompanyService = new AccompanyServiceImpl(accompanyRepository);

        Accompany accompany = new Accompany(null, 4L, "hi2", "대전", LocalDate.parse("2023-06-05"), 4, "제목2", "내용2", true);
        when(accompanyRepository.save(any(Accompany.class))).thenReturn(accompany);
        accompanyService.createAccompany(accompany);

        when(accompanyRepository.findById(1L)).thenReturn(Optional.of(accompany));
        Optional<Accompany> res = accompanyService.findById(1L);

        assertThat(res.get().getNickname()).isEqualTo("hi2");
    }

}
