package wanderhub.server.domain.accompany.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import wanderhub.server.auth.utils.CustomAuthorityUtils;
import wanderhub.server.domain.accompany.dto.AccompanyDto;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.service.AccompanyService;
import wanderhub.server.domain.member.entity.Member;
import wanderhub.server.domain.member.entity.MemberStatus;
import wanderhub.server.domain.member.repository.MemberRepository;
import wanderhub.server.domain.member.service.MemberService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccompanyController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs //rest docs 자동 설정
@WithMockUser
public class AccompanyControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccompanyService accompanyService;

    @MockBean
    private MemberService memberService;

    private static final LocalDateTime time = LocalDateTime.now();

    @Test
    @DisplayName("생성")
    public void create() throws Exception {
//        Member m1 = new Member(1L, "name", "22@gmail.com", "nickname","img","local", null, MemberStatus.ACTIVE);
        Member m1 = Member.builder()
                .Id(1L)
                .name("name")
                .email("email@gmail.com")
                .nickName("nickname")
                .build();
        memberService.createMember(m1);

        AccompanyDto dto1 = AccompanyDto.builder()
                .memberId(m1.getId())
                .accompanyLocal("서울")
                .accompanyDate(LocalDate.parse("2023-06-05"))
                .maxNum(2)
                .accompanyTitle("제목1")
                .accompanyContent("본문1")
                .build();
//        accompanyService.createAccompany(AccompanyMapper.INSTANCE.toEntity(dto1)); //왜 이게 없어도 작동이 잘 될까...

//        when(accompanyService.createAccompany()).thenReturn(Optional.of(AccompanyMapper.INSTANCE.fromResponseDtotoEntity(dto1)));

        this.mockMvc.perform(post("/accompany")
                        .with(csrf())
                        .content(mapper.writeValueAsString(dto1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("accompany/create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").description("member id"),
//                                subsectionWithPath("member").description("생성한 멤버"),
                                fieldWithPath("accompanyLocal").description("동행할 지역"),
                                fieldWithPath("accompanyDate").description("동행할 날짜"),
                                fieldWithPath("maxNum").description("동행할 최대 인원"),
                                fieldWithPath("accompanyTitle").description("동행글 제목"),
                                fieldWithPath("accompanyContent").description("동행글 본문")
                        )));
    }

    @Test
    @DisplayName("전체 조회")
    public void findAll() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        Member m2 = new Member(2L, "name2", "2222@gmail.com", "nickname2","img2","local2", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);
        memberService.createMember(m2);

        LocalDateTime createdAt = time;
        LocalDateTime modifiedAt = LocalDateTime.now();
        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-07-01"), 2, "제목1", "본문1", true, createdAt, modifiedAt);
        AccompanyResponseDto dto2 = new AccompanyResponseDto(2L, m2.getNickName(), "대구", LocalDate.parse("2023-07-02"), 3, "제목2", "본문2", true, createdAt, modifiedAt);

        List<AccompanyResponseDto> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        when(accompanyService.findAll()).thenReturn(AccompanyMapper.INSTANCE.fromResponseDtotoEntityList(list));

        this.mockMvc.perform(get("/accompany")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany/findAll",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].nickname").description("글 생성자 닉네임"),
                                fieldWithPath("[].accompanyLocal").description("동행할 지역"),
                                fieldWithPath("[].accompanyDate").description("동행할 날짜"),
                                fieldWithPath("[].maxNum").description("동행할 최대 인원"),
                                fieldWithPath("[].accompanyTitle").description("동행글 제목"),
                                fieldWithPath("[].accompanyContent").description("동행글 본문"),
                                fieldWithPath("[].openStatus").description("모집 상태"),
                                fieldWithPath("[].createdAt").description("생성된 날짜"),
                                fieldWithPath("[].modifiedAt").description("수정된 날짜")
                        )));
    }

    @Test
    @DisplayName("동행글 식별자로 조회")
    public void findById() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);

        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-06-11"), 5, "제목1", "본문1", true, time, time);

        when(accompanyService.findById(anyLong())).thenReturn(Optional.of(AccompanyMapper.INSTANCE.fromResponseDtotoEntity(dto1)));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/{id}", dto1.getId()) //pathParameters 쓰려면 RestDocumentationRequestBuilders 사용해야 함
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany/findById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("동행글 식별자")
                        ),
                        responseFields(
                                fieldWithPath("id").description("accompany id (PK)"),
                                fieldWithPath("nickname").description("글 생성자 닉네임"),
                                fieldWithPath("accompanyLocal").description("동행할 지역"),
                                fieldWithPath("accompanyDate").description("동행할 날짜"),
                                fieldWithPath("maxNum").description("동행할 최대 인원"),
                                fieldWithPath("accompanyTitle").description("동행글 제목"),
                                fieldWithPath("accompanyContent").description("동행글 본문"),
                                fieldWithPath("openStatus").description("모집 상태"),
                                fieldWithPath("createdAt").description("생성된 날짜"),
                                fieldWithPath("modifiedAt").description("수정된 날짜")
                        )));
    }

    @Test
    @DisplayName("지역 별 조회")
    public void findByLocal() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        Member m2 = new Member(2L, "name2", "2222@gmail.com", "nickname2","img2","local2", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);
        memberService.createMember(m2);

        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-07-01"), 2, "제목1", "본문1", true, time, time);
        AccompanyResponseDto dto2 = new AccompanyResponseDto(2L, m2.getNickName(), "서울", LocalDate.parse("2023-07-02"), 3, "제목2", "본문2", true, time, time);
        List<AccompanyResponseDto> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        when(accompanyService.findByLocal("서울")).thenReturn(AccompanyMapper.INSTANCE.fromResponseDtotoEntityList(list));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bylocal?accompanyLocal=서울", dto1.getAccompanyLocal())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany/findByLocal",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("accompanyLocal").description("동행 지역")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].nickname").description("글 생성자 닉네임"),
                                fieldWithPath("[].accompanyLocal").description("동행할 지역"),
                                fieldWithPath("[].accompanyDate").description("동행할 날짜"),
                                fieldWithPath("[].maxNum").description("동행할 최대 인원"),
                                fieldWithPath("[].accompanyTitle").description("동행글 제목"),
                                fieldWithPath("[].accompanyContent").description("동행글 본문"),
                                fieldWithPath("[].openStatus").description("모집 상태"),
                                fieldWithPath("[].createdAt").description("생성된 날짜"),
                                fieldWithPath("[].modifiedAt").description("수정된 날짜")
                        )));
    }

    @Test
    @DisplayName("날짜 별 조회")
    public void findByDate() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        Member m2 = new Member(2L, "name2", "2222@gmail.com", "nickname2","img2","local2", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);
        memberService.createMember(m2);

        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-07-02"), 2, "제목1", "본문1", true, time, time);
        AccompanyResponseDto dto2 = new AccompanyResponseDto(2L, m2.getNickName(), "서울", LocalDate.parse("2023-07-02"), 3, "제목2", "본문2", true, time, time);
        List<AccompanyResponseDto> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        when(accompanyService.findByDate("2023-07-02")).thenReturn(AccompanyMapper.INSTANCE.fromResponseDtotoEntityList(list));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bydate?accompanyDate=2023-07-02", dto1.getAccompanyDate())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany/findByDate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("accompanyDate").description("동행 날짜")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].nickname").description("글 생성자 닉네임"),
                                fieldWithPath("[].accompanyLocal").description("동행할 지역"),
                                fieldWithPath("[].accompanyDate").description("동행할 날짜"),
                                fieldWithPath("[].maxNum").description("동행할 최대 인원"),
                                fieldWithPath("[].accompanyTitle").description("동행글 제목"),
                                fieldWithPath("[].accompanyContent").description("동행글 본문"),
                                fieldWithPath("[].openStatus").description("모집 상태"),
                                fieldWithPath("[].createdAt").description("생성된 날짜"),
                                fieldWithPath("[].modifiedAt").description("수정된 날짜")
                        )));
    }

    @Test
    @DisplayName("지역&날짜 별 조회")
    public void findByLocalAndDate() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        Member m2 = new Member(2L, "name2", "2222@gmail.com", "nickname2","img2","local2", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);
        memberService.createMember(m2);

        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-07-02"), 2, "제목1", "본문1", true, time, time);
        AccompanyResponseDto dto2 = new AccompanyResponseDto(2L, m2.getNickName(), "서울", LocalDate.parse("2023-07-02"), 3, "제목2", "본문2", true, time, time);
        List<AccompanyResponseDto> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        when(accompanyService.findByLocalAndDate("서울","2023-07-02")).thenReturn(AccompanyMapper.INSTANCE.fromResponseDtotoEntityList(list));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bylocalanddate?accompanyLocal=서울&accompanyDate=2023-07-02")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany/findByLocalAndDate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("accompanyLocal").description("동행 지역"),
                                parameterWithName("accompanyDate").description("동행 날짜")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].nickname").description("글 생성자 닉네임"),
                                fieldWithPath("[].accompanyLocal").description("동행할 지역"),
                                fieldWithPath("[].accompanyDate").description("동행할 날짜"),
                                fieldWithPath("[].maxNum").description("동행할 최대 인원"),
                                fieldWithPath("[].accompanyTitle").description("동행글 제목"),
                                fieldWithPath("[].accompanyContent").description("동행글 본문"),
                                fieldWithPath("[].openStatus").description("모집 상태"),
                                fieldWithPath("[].createdAt").description("생성된 날짜"),
                                fieldWithPath("[].modifiedAt").description("수정된 날짜")
                        )));
    }

    @Test
    @DisplayName("삭제")
    public void deleteAccompany() throws Exception {
        Member m1 = new Member(1L, "name1", "1111@gmail.com", "nickname1","img1","local1", null, MemberStatus.ACTIVE);
        memberService.createMember(m1);

        AccompanyResponseDto dto1 = new AccompanyResponseDto(1L, m1.getNickName(), "서울", LocalDate.parse("2023-07-02"), 2, "제목1", "본문1", true, time, time);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/accompany/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(document("accompany/delete",
                        pathParameters(
                                parameterWithName("id").description("동행글 식별자")
                        )));
    }

}
