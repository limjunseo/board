package com.jun.board_project.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String save(User user) {
        userService.save(user);
        return "redirect:/";
    }
}
