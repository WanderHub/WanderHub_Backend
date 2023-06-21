package wanderhub.server.domain.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanderhub.server.global.utils.Local;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BoardDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private String local;;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private String title;
        private String content;
        private String local;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long boardId;
        private String nickName;
        private String title;
        private String content;
        private String local;
        private Long viewPoint;
        //        private Long likePoint;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

}
