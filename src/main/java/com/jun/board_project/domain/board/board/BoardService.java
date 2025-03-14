package com.jun.board_project.domain.board.board;

import com.jun.board_project.domain.board.board.dto.BoardRequestDto;
import com.jun.board_project.domain.board.board.dto.BoardInfo;
import com.jun.board_project.domain.board.board.dto.BoardMainPageInfo;
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
    public int save(BoardRequestDto boardRequestDto)   {
        //max + 1 채번
        int boardId = boardRepository.nextVal();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Board board = Board.builder()
                .boardId(boardId)
                .boardTitle(boardRequestDto.getBoardTitle())
                .memberId(boardRequestDto.getMemberId())
                .boardCtId(boardRequestDto.getBoardCtId())
                .build();
        boardRepository.save(board);

        BoardDetail boardDetail = BoardDetail.builder()
                .boardId(boardId)
                .boardContent(boardRequestDto.getBoardContent())
                .build();
        boardDetailRepository.save(boardDetail);

        return boardId;
    }

    public BoardInfo getBoard(int boardId) {
        return boardRepository.findById(boardId);
    }

    //게시글 좋아요 기능
    public void saveBoardLike(BoardLike boardLike) {
        boardLikeRepository.saveBoardLike(boardLike);
    }

    public List<BoardMainPageInfo> getBoardList(String boardCtId, int page) {

        //HOT 게시판 조회
        if(boardCtId.equals("04")) {
            return boardRepository.findHotBoard(boardCtId, page);
        }
        return boardRepository.findAllByBoardCtId(boardCtId, page);
    }

    public List<BoardMainPageInfo> getBookmarkedBoardList(String memberId) {
        return boardRepository.findBookmarkedBoardList(memberId);
    }




}
