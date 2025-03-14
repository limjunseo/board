package com.jun.board_project.domain.board.board;

import com.jun.board_project.domain.board.board.dto.BoardRequestDto;
import com.jun.board_project.domain.board.board.dto.BoardInfo;
import com.jun.board_project.domain.board.boardCt.BoardCtDto;
import com.jun.board_project.domain.board.boardCt.BoardCtIdRepository;
import com.jun.board_project.domain.board.boardCt.BoardCtPageDto;
import com.jun.board_project.domain.boardBookmark.BoardBookmarkService;
import com.jun.board_project.domain.boardComment.dto.BoardCommentInfo;
import com.jun.board_project.domain.boardComment.BoardCommentService;
import com.jun.board_project.domain.boardCommentLike.dto.BoardCommentLikeInfo;
import com.jun.board_project.domain.boardLike.BoardLike;
import com.jun.board_project.domain.boardLike.BoardLikeService;
import com.jun.board_project.domain.member.member.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final BoardCtIdRepository boardCtIdRepository;
    private final BoardCommentService boardCommentService;
    private final BoardBookmarkService boardBookmarkService;
    private final BoardLikeService boardLikeService;


    //새 글 작성 페이지
    @RequestMapping(value = "/board/{boardCtId}/new", method = RequestMethod.GET)
    public String getBoardNewFormPage(@ModelAttribute BoardRequestDto boardRequestDto) {
        //새 글 기본양식
        boardRequestDto.setDefault();

        return "board/newBoardForm";
    }

    //새글 작성 POST 요청
    @RequestMapping(value = "/board/{boardCtId}/new", method = RequestMethod.POST)
    public String save(@AuthenticationPrincipal MemberDetails member, @ModelAttribute BoardRequestDto boardRequestDto) {
        if(!member.isEnabled()) {
            return "redirect:/login";
        }

        boardRequestDto.setMemberId(member.getUsername());

        boardService.save(boardRequestDto);
        return "redirect:/";
    }


    //특정 카테고리 게시글 리스트 조회
    @RequestMapping(value = "/board/{boardCtId}", method = RequestMethod.GET)
    public String getBoardList(@PathVariable("boardCtId") String boardCtId,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               ModelMap model) {


        List<BoardCtPageDto> boardList = boardService.getBoardList(boardCtId, page);
        BoardCtDto boardCtDto = boardCtIdRepository.findByCtId(boardCtId);

        model.addAttribute("currentPage", page);     // 현재 페이지 번호
        model.addAttribute("boardCtDto", boardCtDto);
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    //게시글과 게시글 상세 정보 조회
    @RequestMapping(value = "/board/{boardCtId}/{boardId}", method = RequestMethod.GET)
    public String getBoard(@PathVariable("boardId") int boardId, @PathVariable("boardCtId") String boardCtId,
                           @AuthenticationPrincipal MemberDetails member, ModelMap model) {
        BoardInfo boardInfo = boardService.getBoard(boardId);
        List<BoardCommentInfo> boardCommentListDto = boardCommentService.findCommentByBoardId(boardId);
        List<BoardCommentLikeInfo> boardCommentLikeListDto = boardCommentService.findLikedBoardCommentByBoardIdAndMemberId(boardId, member.getUsername());
        String bookmarkYn = boardBookmarkService.findBookmarkYn(boardId, member.getUsername());
        String likeYn = boardLikeService.findLikeYn(boardId, member.getUsername());


        boardInfo.setBookmarkYn(bookmarkYn); //북마크 여부설정
        boardInfo.setLikeYn(likeYn);

        //좋아요한 댓글 is liked true 설정
        for (BoardCommentInfo boardCommentInfo : boardCommentListDto) {
            for (BoardCommentLikeInfo boardCommentLikeInfo : boardCommentLikeListDto) {
                if (boardCommentInfo.getCommentId() == boardCommentLikeInfo.getCommentId() && boardCommentInfo.getCommentSeq() == boardCommentLikeInfo.getCommentSeq()) {
                    boardCommentInfo.setLiked(true);
                }
            }
        }


        //게시글 좋아요숫자, 좋아요여부
        model.addAttribute("bookmarkYn", bookmarkYn); //북마크 여부
        model.addAttribute("boardCommentList", boardCommentListDto); //댓글 리스트
        model.addAttribute("board", boardInfo); //게시글 상세내용
        return "board/boardDetail";
    }


    //게시글 좋아요 요청
    @RequestMapping(value = "/board/{boardCtId}/{boardId}/like", method = RequestMethod.POST)
    public String like(@PathVariable("boardId") int boardId,
                       @PathVariable("boardCtId") String boardCtId,
                       @AuthenticationPrincipal MemberDetails member) {

        BoardLike boardLike = BoardLike.builder()
                .boardId(boardId)
                .memberId(member.getUsername())
                .build();
        boardService.saveBoardLike(boardLike);
        return "redirect:/";
    }


    


}