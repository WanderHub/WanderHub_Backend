package wanderhub.server.domain.accompany.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccompanyDto {
    private Long id;
    private Long memberId;
    private String writerName;
    private String accompanyLocal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate accompanyDate;
    private int maxNum;
    private String accompanyTitle;
    private String accompanyContent;
    private boolean openStatus;

/* 수동방법
    //dto -> entity
    public Accompany toEntity() {
        return Accompany.builder()
                .id(id)
                .memberId(memberId)
                .writerName(writerName)
                .accompanyLocal(accompanyLocal)
                .accompanyDate(accompanyDate)
                .maxNum(maxNum)
                .accompanyTitle(accompanyTitle)
                .accompanyContent(accompanyContent)
                .openStatus(openStatus)
                .build();
    }

    //entity -> dto
    public static AccompanyDto fromEntity(Accompany accompany) {
        return AccompanyDto.builder()
                .id(accompany.getId())
                .memberId(accompany.getMemberId())
                .writerName(accompany.getWriterName())
                .accompanyLocal(accompany.getAccompanyLocal())
                .accompanyDate(accompany.accompanyDate)
                .maxNum(accompany.getMaxNum())
                .accompanyTitle(accompany.getAccompanyTitle())
                .accompanyContent(accompany.getAccompanyContent())
                .openStatus(accompany.isOpenStatus())
                .build();
    }*/

}