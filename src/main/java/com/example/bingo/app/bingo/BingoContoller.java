package com.example.bingo.app.bingo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.bingo.app.bingoroom.BingoRoomForm;
import com.example.bingo.domain.model.BingoCandidate;
import com.example.bingo.domain.model.BingoResult;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.service.bingo.BingoService;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("/host/bingoRoom/{bingoRoomIdstr}/bingoGame")
public class BingoContoller {

    @Inject
    BingoRoomService bingoRoomService;

    @Inject
    BingoService bingoService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public BingoRoomForm setUpBingoRoomForm() {
        BingoRoomForm form = new BingoRoomForm();
        return form;
    }

    @ModelAttribute
    public BingoForm setUpBingoForm() {
        BingoForm form = new BingoForm();
        return form;
    }

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

        model.addAttribute("bingoRoom", bingoRoom);

        List<BingoResult> bingoResultList = bingoService.findAllByBingoRoom(bingoRoom).stream()
                .sorted(Comparator.comparing(BingoResult::getCreatedAt)).collect(Collectors.toList());

        model.addAttribute("bingoResultList", bingoResultList);

        BingoCandidate[][] bingoCandidateList = new BingoCandidate[10][10];
        for (int bingoValue = 0; bingoValue < 100; bingoValue++) {
            bingoCandidateList[bingoValue / 10][bingoValue % 10] = new BingoCandidate(bingoValue + "", false);

            for (BingoResult bingoResult : bingoResultList) {
                if (bingoResult.getBingoValue()
                        .equals(bingoCandidateList[bingoValue / 10][bingoValue % 10].getBingoValue())) {
                    bingoCandidateList[bingoValue / 10][bingoValue % 10].setResulted(true);
                }
            }
        }

        model.addAttribute("bingoCandiateList", bingoCandidateList);

        return "bingogame/index";
    }

    @PostMapping("lottery")
    public String lottery(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes,
            @Valid BingoForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return index(bingoRoomIdstr, model, attributes);
        }

        BingoRoom bingoRoom = bingoRoomService.findByBingoRoomId(form.getBingoRoomId());
        List<BingoResult> bingoResultList = bingoService.findAllByBingoRoom(bingoRoom);
        BingoResult bingoResult = beanMapper.map(form, BingoResult.class);

        if (bingoResultList.stream().anyMatch(result -> result.getBingoValue().equals(bingoResult.getBingoValue()))) {
            return index(bingoRoomIdstr, model, attributes);
        }
        bingoResult.setBingoRoom(bingoRoom);
        bingoService.create(bingoResult);

        return "redirect:/host/bingoRoom/" + bingoRoom.getBingoRoomId() + "/bingoGame";
    }
}
