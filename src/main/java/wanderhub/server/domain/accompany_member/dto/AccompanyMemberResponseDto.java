package wanderhub.server.domain.accompany_member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccompanyMemberResponseDto {

    private Long id;
    private Long accompanyId;
    private Long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
