package com.example.bingo.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン用 Controller
 * 
 * @author takuminv
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * ログイン画面
     * 
     * @return
     */
    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/loginForm";
    }

}
