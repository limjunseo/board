package com.jun.board_project.domain.main;

import com.jun.board_project.domain.board.BoardCtDto;
import com.jun.board_project.domain.board.BoardCtIdRepository;
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

    //게시판 메인 페이지
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        List<BoardCtDto> boardCtDtoList = boardCtIdRepository.findByCdTypeId("001");

        model.put("boardCtList", boardCtDtoList);
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "admin/dashboard";
    }
    


}
