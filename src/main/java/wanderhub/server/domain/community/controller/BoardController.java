package wanderhub.server.domain.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wanderhub.server.domain.community.dto.BoardDto;
import wanderhub.server.domain.community.entity.Board;
import wanderhub.server.domain.community.mapper.BoardMapper;
import wanderhub.server.domain.community.service.BoardService;
import wanderhub.server.global.response.SingleResponse;

import java.security.Principal;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/community")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    // 게시판 작성
    @PostMapping
    public ResponseEntity boardPost(Principal principal, @Validated @RequestBody BoardDto.Post post) {
        // mapper로 Entity
        Board postBoard = boardMapper.boardPostDtoToBoard(post);
        String email = principal.getName();
        Board createdBoard = boardService.createBoard(postBoard, email);
        BoardDto.Response response = boardMapper.boardToBoardResponseDto(createdBoard);
        return new ResponseEntity(new SingleResponse<>(response), HttpStatus.CREATED);
    }

    // 게시판 수정
    @PatchMapping("/{board-id}")
    public ResponseEntity boardPatch(@PathVariable("board-id")Long boardId, Principal principal, @Validated @RequestBody BoardDto.Patch patch) {
        // mapper로 Entity화
        Board patchBoard = boardMapper.boardPatchDtoToBoard(patch);
        String email = principal.getName();
        Board updatedBoard = boardService.updateBoard(boardId, patchBoard, email);
        return ResponseEntity.ok(boardMapper.boardToBoardResponseDto(updatedBoard));
    }

    // 게시판 삭제
    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id")Long boardId, Principal principal) {
        boardService.removeBoard(boardId, principal.getName());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 게시판 단일 조회
    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id")Long boardId) {
        Board board = boardService.getBoard(boardId);
        BoardDto.Response response = boardMapper.boardToBoardResponseDto(board);
        return new ResponseEntity(new SingleResponse<>(response), HttpStatus.OK);
    }


//     게시판 전체 조회
//     페이지 처리 물어보기

}
