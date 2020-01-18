package com.example.bingo.app.bingoroom;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;

@Controller
@RequestMapping("/host/bingoRoom/{bingoRoomIdstr}")
public class BingoRoomController {
	
	@Inject 
	BingoRoomService bingoRoomService;
	
	@GetMapping
	public String index(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {
		
		try {
			long bingoRoomId = Long.parseLong(bingoRoomIdstr);
		    BingoRoom bingoRoom = bingoRoomService.findByBingoRoomId(bingoRoomId);
		    model.addAttribute("bingoRoom", bingoRoom);
			return "bingoroom/index";
			
		} catch(ResourceNotFoundException e) {
			attributes.addFlashAttribute(ResultMessages.error()
					.add(ResultMessage.fromText(e.getMessage())));
		
		} catch(NumberFormatException e) {
			attributes.addFlashAttribute(ResultMessages.error()
					.add(ResultMessage.fromText("Idが無効です。")));
		}
		
		return "redirect:/host/home";
		
	}

}
