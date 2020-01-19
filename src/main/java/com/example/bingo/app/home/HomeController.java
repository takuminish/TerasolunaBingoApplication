package com.example.bingo.app.home;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bingo.app.bingoroom.BingoRoomForm;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;
import com.example.bingo.domain.service.useraccountdetails.UserAccountDetails;

@Controller
@RequestMapping("/host/home")
public class HomeController {

    @Inject
    BingoRoomService bingoRoomService;

    @ModelAttribute
    public BingoRoomForm setUpForm() {
        BingoRoomForm form = new BingoRoomForm();
        return form;
    }

    /**
     * ログインしたユーザのホーム画面を返す
     * 
     * @param userAccountDetails
     * @param model
     * @return
     */
    @GetMapping
    public String index(@AuthenticationPrincipal UserAccountDetails userAccountDetails, Model model) {

        // 認証したログインユーザを取得
        UserAccount userAccount = userAccountDetails.getUserAccount();

        // ログインユーザが登録したBingoRoomを全て取得し登録日時の逆順で表示させる
        List<BingoRoom> bingoRoomList = bingoRoomService.findAllByCreateUser(userAccount).stream()
                .sorted(Comparator.comparing(BingoRoom::getCreatedAt).reversed()).collect(Collectors.toList());
        model.addAttribute("bingoRoomList", bingoRoomList);

        return "/home/index";
    }
}
