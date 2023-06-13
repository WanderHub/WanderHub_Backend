package wanderhub.server.domain.accompany.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import wanderhub.server.domain.accompany.dto.AccompanyResponseDto;
import wanderhub.server.domain.accompany.entity.Accompany;
import wanderhub.server.domain.accompany.mapper.AccompanyMapper;
import wanderhub.server.domain.accompany.service.AccompanyService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
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
//@SpringBootTest(classes = AccompanyController.class)
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

    @Test
    @DisplayName("전체 조회")
    public void findAll() throws Exception {
        Accompany accompany1 = new Accompany(1L, 4L, "hi1", "서울", LocalDate.parse("2023-06-11"), 5, "제목1", "내용1", true);
        Accompany accompany2 = new Accompany(2L, 7L, "hi2", "서울", LocalDate.parse("2023-07-05"), 3, "제목2", "내용2", true);
        List<Accompany> list = new ArrayList<>();
        list.add(accompany1);
        list.add(accompany2);

        when(accompanyService.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/accompany/")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany-findAll",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].memberId").description("글 생성자 id"),
                                fieldWithPath("[].writerName").description("글 생성자 닉네임"),
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
    @DisplayName("생성")
    public void create() throws Exception {
        Accompany accompany1 = Accompany.builder()
                .id(1L)
                .memberId(4L)
                .writerName("hi1")
                .accompanyLocal("서울")
                .accompanyDate(LocalDate.parse("2023-06-05"))
                .maxNum(2)
                .accompanyTitle("제목1")
                .accompanyContent("본문1")
                .openStatus(true)
                .build();

        this.mockMvc.perform(post("/accompany/")
                        .with(csrf())
                        .content(mapper.writeValueAsString(accompany1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("accompany-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("id").description("accompany id (PK)").ignored(),
                                fieldWithPath("memberId").description("글 생성자 id"),
                                fieldWithPath("writerName").description("글 생성자 닉네임"),
                                fieldWithPath("accompanyLocal").description("동행할 지역"),
                                fieldWithPath("accompanyDate").description("동행할 날짜"),
                                fieldWithPath("maxNum").description("동행할 최대 인원"),
                                fieldWithPath("accompanyTitle").description("동행글 제목"),
                                fieldWithPath("accompanyContent").description("동행글 본문"),
                                fieldWithPath("openStatus").description("모집 상태").ignored(),
                                fieldWithPath("createdAt").description("생성된 날짜").ignored(),
                                fieldWithPath("modifiedAt").description("수정된 날짜").ignored()
                        )));
    }

    @Test
    @DisplayName("동행글 식별자로 조회")
    public void findById() throws Exception {
        Accompany accompany1 = new Accompany(1L, 4L, "hi1", "서울", LocalDate.parse("2023-06-11"), 5, "제목1", "내용1", true);

        when(accompanyService.findById(anyLong())).thenReturn(Optional.of(accompany1));

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/{id}", accompany1.getId()) //pathParameters 쓰려면 RestDocumentationRequestBuilders 사용해야 함
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany-findById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("동행글 식별자")
                        ),
                        responseFields(
                                fieldWithPath("id").description("accompany id (PK)"),
                                fieldWithPath("memberId").description("글 생성자 id"),
                                fieldWithPath("writerName").description("글 생성자 닉네임"),
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
        Accompany accompany1 = new Accompany(1L, 4L, "hi1", "서울", LocalDate.parse("2023-06-11"), 5, "제목1", "내용1", true);
        Accompany accompany2 = new Accompany(2L, 7L, "hi2", "서울", LocalDate.parse("2023-07-05"), 3, "제목2", "내용2", true);
        List<Accompany> list = new ArrayList<>();
        list.add(accompany1); list.add(accompany2);

        when(accompanyService.findByLocal("서울")).thenReturn(list);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bylocal/{accompanyLocal}", accompany1.getAccompanyLocal())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany-findByLocal",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("accompanyLocal").description("동행 지역")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].memberId").description("글 생성자 id"),
                                fieldWithPath("[].writerName").description("글 생성자 닉네임"),
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
        Accompany accompany3 = new Accompany(3L, 9L, "hi9", "제주", LocalDate.parse("2023-07-10"), 2, "제목3", "내용3", true);
        Accompany accompany4 = new Accompany(4L, 8L, "hi8", "제주", LocalDate.parse("2023-07-10"), 4, "제목4", "내용4", true);
        List<Accompany> list = new ArrayList<>();
        list.add(accompany3); list.add(accompany4);

        when(accompanyService.findByDate("2023-07-10")).thenReturn(list);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bydate/{accompanyDate}", accompany3.getAccompanyDate())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany-findByDate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("accompanyDate").description("동행 날짜")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].memberId").description("글 생성자 id"),
                                fieldWithPath("[].writerName").description("글 생성자 닉네임"),
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

//    @Test
//    @DisplayName("지역&날짜 별 조회")
//    public void findByLocalAndDate() throws Exception {
//        Accompany accompany3 = new Accompany(3L, 9L, "hi9", "제주", LocalDate.parse("2023-07-10"), 2, "제목3", "내용3", true);
//        Accompany accompany4 = new Accompany(4L, 8L, "hi8", "제주", LocalDate.parse("2023-07-10"), 4, "제목4", "내용4", true);
//        List<Accompany> list = new ArrayList<>();
//        list.add(accompany3); list.add(accompany4);
//
//        when(accompanyService.findByLocalAndDate("제주","2023-07-10")).thenReturn(list);
//
//        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bylocalanddate/{accompanyLocal}/{accompanyDate}", accompany3.getAccompanyLocal(), accompany4.getAccompanyDate())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("accompany-findByLocalAndDate",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                parameterWithName("accompanyLocal").description("동행 지역"),
//                                parameterWithName("accompanyDate").description("동행 날짜")
//                        ),
//                        responseFields(
//                                fieldWithPath("[].id").description("accompany id (PK)"),
//                                fieldWithPath("[].memberId").description("글 생성자 id"),
//                                fieldWithPath("[].writerName").description("글 생성자 닉네임"),
//                                fieldWithPath("[].accompanyLocal").description("동행할 지역"),
//                                fieldWithPath("[].accompanyDate").description("동행할 날짜"),
//                                fieldWithPath("[].maxNum").description("동행할 최대 인원"),
//                                fieldWithPath("[].accompanyTitle").description("동행글 제목"),
//                                fieldWithPath("[].accompanyContent").description("동행글 본문"),
//                                fieldWithPath("[].openStatus").description("모집 상태"),
//                                fieldWithPath("[].createdAt").description("생성된 날짜"),
//                                fieldWithPath("[].modifiedAt").description("수정된 날짜")
//                        )));
//    }

    @Test
    @DisplayName("지역&날짜 별 조회, 쿼리스트링으로")
    public void findByLocalAndDate2() throws Exception {
        Accompany accompany3 = new Accompany(3L, 9L, "hi9", "제주", LocalDate.parse("2023-07-10"), 2, "제목3", "내용3", true);
        Accompany accompany4 = new Accompany(4L, 8L, "hi8", "제주", LocalDate.parse("2023-07-10"), 4, "제목4", "내용4", true);
        List<Accompany> list = new ArrayList<>();
        list.add(accompany3); list.add(accompany4);

        when(accompanyService.findByLocalAndDate("제주","2023-07-10")).thenReturn(list);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/accompany/bylocalanddate2?accompanyLocal=제주&accompanyDate=2023-07-10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("accompany-findByLocalAndDate2",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("accompanyLocal").description("동행 지역"),
                                parameterWithName("accompanyDate").description("동행 날짜")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("accompany id (PK)"),
                                fieldWithPath("[].memberId").description("글 생성자 id"),
                                fieldWithPath("[].writerName").description("글 생성자 닉네임"),
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
        Accompany accompany1 = new Accompany(1L, 4L, "hi1", "서울", LocalDate.parse("2023-06-11"), 5, "제목1", "내용1", true);
        AccompanyResponseDto a1 = AccompanyMapper.INSTANCE.toDto(accompany1);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/accompany/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(document("accompany-delete",
                        pathParameters(
                                parameterWithName("id").description("동행글 식별자")
                        )));
    }


}
