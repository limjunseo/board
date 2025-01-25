package com.jun.board_project.domain.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    //게시판 메인 페이지
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "index";
    }

    @RequestMapping(value ="/board/{board_ct_cd}", method = RequestMethod.GET)
    public String getBoardPage(@PathVariable("board_ct_cd") String board_ct_cd, ModelMap model) {


        return "board";
    }
}
