package com.example.bingo.app.home;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;
import com.example.bingo.domain.service.useraccountdetails.UserAccountDetails;

@Controller
@RequestMapping("/host/home")
public class HomeController {

	@Inject
	BingoRoomService bingoRoomService;
	

	/**
	 *  ログインしたユーザのホーム画面を返す
	 * @param userAccountDetails
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(@AuthenticationPrincipal UserAccountDetails userAccountDetails,
			Model model) {
		
		// 認証したログインユーザを取得
		UserAccount userAccount = userAccountDetails.getUserAccount();
		
		// ログインユーザが登録したBingoRoomを全て取得
		List<BingoRoom> bingoRoomList = bingoRoomService.findAllByCreateUser(userAccount);
		model.addAttribute("bingoRoomList", bingoRoomList);
		
		return "/home/index";
	}
}
