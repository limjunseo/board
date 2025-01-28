package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardDetail.BoardDetail;
import com.jun.board_project.domain.boardDetail.BoardDetailRepository;
import com.jun.board_project.domain.boardLike.BoardLike;
import com.jun.board_project.domain.boardLike.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardDetailRepository boardDetailRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public int save(BoardForm boardForm) {
        //max + 1 채번
        int boardId = boardRepository.nextVal();
        
        Board board = Board.builder()
                .boardId(boardId)
                .boardTitle(boardForm.getBoardTitle())
                .memberId(boardForm.getMemberId())
                .boardCtId(boardForm.getBoardCtId())
                .build();
        boardRepository.save(board);

        BoardDetail boardDetail = BoardDetail.builder()
                .boardId(boardId)
                .boardContent(boardForm.getBoardContent())
                .build();
        boardDetailRepository.save(boardDetail);

        return boardId;
    }

    public Board getBoard(int boardId) {
        return boardRepository.findById(boardId);
    }

    //좋아요 기능
    public void like(BoardLike boardLike) {
        boardLikeRepository.insert(boardLike);
    }

    public List<Board> getBoardList(String boardCtId) {
        return boardRepository.findAllByBoardCtId(boardCtId);
    }
}
