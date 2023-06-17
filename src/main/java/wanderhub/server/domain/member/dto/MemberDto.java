package wanderhub.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import wanderhub.server.domain.member.entity.MemberStatus;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class MemberDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private String name;            // 이름 / null 허용
        @NotBlank
        private String nickName;     // 닉네임 / null, 공백, 빈문자 X
        private String imgUrl;          // 이미지 Url  / null 허용
        private String local;           // 지역 / null 허용
    }

    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long Id;
        private String name;
        private String email;
        private String nickName;
        private String imgUrl;
        private String local;
        private MemberStatus memberStatus;
    }


}
