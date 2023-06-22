package wanderhub.server.domain.accompany_member.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccompanyMemberResponseDto {

    private Long id;
    private Long accompanyId;
    private Long memberId;


}
