package com.jun.board_project.domain.main;

import com.jun.board_project.domain.board.board.dto.BoardCtInfo;
import com.jun.board_project.domain.board.boardCt.BoardCtIdRepository;
import com.jun.board_project.domain.member.topMember.dto.MonthTopMemberInfo;
import com.jun.board_project.domain.member.topMember.dto.TopMemberInfo;
import com.jun.board_project.domain.member.topMember.TopMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@AllArgsConstructor
@Controller
public class MainController {
    private final BoardCtIdRepository boardCtIdRepository;
    private final TopMemberService topMemberService;

    //게시판 메인 페이지
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        List<BoardCtInfo> boardCtInfoList = boardCtIdRepository.findByCdTypeId("001");
        List<TopMemberInfo> yesterdayTopMembers = topMemberService.findYesterdayTopMember(); //어제 우수멤버 조회
        List<MonthTopMemberInfo> monthTopMembers = topMemberService.findThisMonthTopMember(); //월간 우수멤버 조회

        model.put("monthTopMembers", monthTopMembers); //월간 우수멤버
        model.put("yesterdayTopMembers", yesterdayTopMembers); //어제 우수멤버
        model.put("boardCtList", boardCtInfoList);
        return "index";
    }


    


}
