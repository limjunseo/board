package com.jun.board_project.domain.board;

import com.jun.board_project.domain.boardBookmark.BoardBookmarkService;
import com.jun.board_project.domain.boardComment.BoardCommentDto;
import com.jun.board_project.domain.boardComment.BoardCommentService;
import com.jun.board_project.domain.boardCommentLike.BoardCommentLikeDto;
import com.jun.board_project.domain.boardCommentLike.BoardCommentLikeRepository;
import com.jun.board_project.domain.boardLike.BoardLike;
import com.jun.board_project.domain.boardLike.BoardLikeService;
import com.jun.board_project.domain.member.MemberDetails;
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
    private final BoardCtIdRepository  boardCtIdRepository;
    private final BoardCommentService boardCommentService;
    private final BoardBookmarkService boardBookmarkService;
    private final BoardLikeService boardLikeService;


    //새 글 작성 페이지
    @RequestMapping(value = "/board/{boardCtId}/new", method = RequestMethod.GET)
    public String getBoardNewFormPage(@ModelAttribute BoardForm boardForm) {
        //새 글 기본양식
        boardForm.setDefault();

        return "board/newBoardForm";
    }

    //새글 작성 POST 요청
    @RequestMapping(value = "/board/{boardCtId}/new", method = RequestMethod.POST)
    public String save(@AuthenticationPrincipal MemberDetails member, @ModelAttribute BoardForm boardForm) {
        if(!member.isEnabled()) {
            return "redirect:/login";
        }

        boardForm.setMemberId(member.getUsername());

        boardService.save(boardForm);
        return "redirect:/";
    }


    //특정 카테고리 게시글 리스트 조회
    @RequestMapping(value = "/board/{boardCtId}", method = RequestMethod.GET)
    public String getBoardList(@PathVariable("boardCtId") String boardCtId,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               ModelMap model) {

        System.out.println("boardCtId : " + boardCtId);
        List<Board> boardList = boardService.getBoardList(boardCtId);
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
        BoardDto boarddto = boardService.getBoard(boardId);
        List<BoardCommentDto> boardCommentListDto = boardCommentService.findCommentByBoardId(boardId);
        List<BoardCommentLikeDto> boardCommentLikeListDto = boardCommentService.findLikedBoardCommentByBoardIdAndMemberId(boardId, member.getUsername());
        String bookmarkYn = boardBookmarkService.findBookmarkYn(boardId, member.getUsername());
        String likeYn = boardLikeService.findLikeYn(boardId, member.getUsername());

        boarddto.setBookmarkYn(bookmarkYn); //북마크 여부설정
        boarddto.setLikeYn(likeYn);

        //좋아요한 댓글 is liked true 설정
        for (BoardCommentDto boardCommentDto : boardCommentListDto) {
            for (BoardCommentLikeDto boardCommentLikeDto : boardCommentLikeListDto) {
                if (boardCommentDto.getCommentId() == boardCommentLikeDto.getCommentId() && boardCommentDto.getCommentSeq() == boardCommentLikeDto.getCommentSeq()) {
                    boardCommentDto.setLiked(true);
                }
            }
        }

        
        //게시글 좋아요숫자, 좋아요여부
        model.addAttribute("bookmarkYn", bookmarkYn); //북마크 여부
        model.addAttribute("boardCommentList", boardCommentListDto); //댓글 리스트
        model.addAttribute("board", boarddto); //게시글 상세내용
        return "board/boardDetail";
    }


    //게시글 좋아요 요청
    @RequestMapping(value = "/board/{boardId}/user/{memberId}", method = RequestMethod.POST)
    public String like(@PathVariable("boardId") int boardId, @PathVariable("memberId") String memberId) {
        BoardLike boardLike = BoardLike.builder()
                .boardId(boardId)
                .memberId(memberId)
                .build();
        boardService.like(boardLike);
        return "redirect:/";
    }


    


}