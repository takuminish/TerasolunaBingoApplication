package com.example.bingo.app.bingo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.bingo.domain.model.Bingo;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.service.bingo.BingoService;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;

@Controller
@RequestMapping("/host/bingoRoom/{bingoRoomIdstr}/bingoGame")
public class BingoContoller {

    @Inject
    BingoRoomService bingoRoomService;

    @Inject
    BingoService bingoService;

    @GetMapping
    public String index(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        BingoRoom bingoRoom;

        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            bingoRoom = bingoRoomService.findByBingoRoomId(bingoRoomId);
        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
            return "redirect:/host/home";

        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
            return "redirect:/host/home";
        }

        if (!bingoRoom.isStarted() || bingoRoom.isFinished()) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("ゲームが開始されていないか、終了しています。")));
            return "redirect:/host/home";
        }

        List<Bingo> bingoList = bingoService.findAllByBingoRoom(bingoRoom).stream()
                .sorted(Comparator.comparing(Bingo::getCreatedAt)).collect(Collectors.toList());

        model.addAttribute("bingoList", bingoList);

        return "bingogame/index";
    }
}
