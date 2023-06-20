package wanderhub.server.domain.community.mapper;

import org.mapstruct.Mapper;
import wanderhub.server.domain.community.dto.BoardDto;
import wanderhub.server.domain.community.entity.Board;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board boardPostDtoToBoard(BoardDto.Post post);

    Board boardPatchDtoToBoard(BoardDto.Patch patch);

    BoardDto.Response boardToBoardResponseDto(Board board);

}
