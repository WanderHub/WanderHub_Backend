package wanderhub.server.domain.accompany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccompanyDto {
    private Long id;
    private Long memberId;
    private String displayName;
    private String local;
    private int maxNum;
    private String title;
    private String content;
    private boolean status;
    private Date createDate;
    private Date modifyDate;

/*
    public Accompany toEntity() {
        return Accompany.builder()
                .id(id)
                .memberId(memberId)
                .displayName(displayName)
                .local(local)
                .maxNum(maxNum)
                .title(title)
                .content(content)
                .status(status)
                .createDate(createDate)
                .modifyDate(modifyDate)
                .build();
    }*/

}