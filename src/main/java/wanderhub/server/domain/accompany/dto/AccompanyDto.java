package wanderhub.server.domain.accompany.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

}