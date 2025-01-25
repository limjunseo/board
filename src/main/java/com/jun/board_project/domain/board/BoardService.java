package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardDetail.BoardDetail;
import com.jun.board_project.domain.boardDetail.BoardDetailRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardDetailRepository boardDetailRepository;

    @Transactional
    public Long save(BoardForm boardForm) {
        //max + 1 채번
        Long boardId = boardRepository.nextVal();
        
        Board board = Board.builder()
                .boardId(boardId)
                .boardTitle(boardForm.getBoardTitle())
                .userId(boardForm.getUserId())
                .build();
        boardRepository.save(board);

        BoardDetail boardDetail = BoardDetail.builder()
                .boardId(boardId)
                .boardContent(boardForm.getBoardContent())
                .build();
        boardDetailRepository.save(boardDetail);

        return boardId;
    }

    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId);
    }

}
