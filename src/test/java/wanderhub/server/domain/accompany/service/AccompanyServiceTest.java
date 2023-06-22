//package wanderhub.server.domain.accompany.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import wanderhub.server.domain.accompany.entity.Accompany;
//import wanderhub.server.domain.accompany.repository.AccompanyRepository;
//import wanderhub.server.domain.member.service.MemberService;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AccompanyServiceTest {
//
//    @Mock
//    private AccompanyRepository accompanyRepository;
//
//    @Mock
//    private MemberService memberService;
//
//    @Test
//    void createAccompany() {
//        AccompanyService accompanyService = new AccompanyServiceImpl(accompanyRepository, memberService);
//
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
//        when(accompanyRepository.save(any(Accompany.class))).thenReturn(accompany1);
//        accompanyService.createAccompany(accompany1);
//
//        when(accompanyRepository.findById(1L)).thenReturn(Optional.of(accompany));
//        Optional<Accompany> res = accompanyService.findById(1L);
//
//        assertThat(res.get().getNickname()).isEqualTo("hi2");
//    }
//
//}