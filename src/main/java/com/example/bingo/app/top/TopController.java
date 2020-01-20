package com.example.bingo.app.top;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * トップ画面用 Controller
 * 
 * @author takuminv
 *
 */
@Controller
@RequestMapping("/")
public class TopController {

    /**
     * トップ画面
     * 
     * @return
     */
    @GetMapping
    public String index() {

        return "top/index";
    }
}
