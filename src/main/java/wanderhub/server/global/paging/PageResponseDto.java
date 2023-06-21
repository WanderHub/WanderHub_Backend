package wanderhub.server.global.paging;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
    List<T> data;
    PageInfo pageInfo;
}