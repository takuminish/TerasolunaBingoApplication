package com.example.bingo.app.bingoroom;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.bingo.app.bingoroom.BingoRoomForm.BingoRoomCreate;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;
import com.example.bingo.domain.service.useraccountdetails.UserAccountDetails;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("/host/bingoRoom")
public class BingoRoomController {

    @Inject
    BingoRoomService bingoRoomService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public BingoRoomForm setUpForm() {
        BingoRoomForm form = new BingoRoomForm();
        return form;
    }

    @GetMapping("{bingoRoomIdstr}/edit")
    public String edit(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            BingoRoom bingoRoom = bingoRoomService.findByBingoRoomId(bingoRoomId);
            model.addAttribute("bingoRoom", bingoRoom);
            return "bingoroom/edit";

        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));

        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }

        return "redirect:/host/home";

    }

    @PostMapping("{bingoRoomIdstr}/edit")
    public String update(@PathVariable String bingoRoomIdstr,
            @Validated({ Default.class, BingoRoomCreate.class }) BingoRoomForm form, BindingResult bindingResult,
            RedirectAttributes attributes) {

        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            BingoRoom bingoRoom = beanMapper.map(form, BingoRoom.class);
            bingoRoom.setBingoRoomId(bingoRoomId);
            bingoRoomService.update(bingoRoom);
            return "redirect:/host/home";

        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));

        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }
        return "redirect:/host/home";
    }

    @GetMapping("create")
    public String createForm() {

        return "/bingoroom/create";
    }

    @PostMapping("create")
    public String create(@Validated({ Default.class, BingoRoomCreate.class }) BingoRoomForm form,
            BindingResult bindingResult, @AuthenticationPrincipal UserAccountDetails userAccountDetails) {

        if (bindingResult.hasErrors()) {
            return "redirect:/host/home";
        }

        BingoRoom bingoRoom = beanMapper.map(form, BingoRoom.class);

        bingoRoom.setCreateUserAccount(userAccountDetails.getUserAccount());
        bingoRoomService.create(bingoRoom);
        return "redirect:/host/home";
    }

    @PostMapping("{bingoRoomIdstr}/start")
    public String start(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {
        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            bingoRoomService.Start(bingoRoomId);
            // return "redirect:/host/bingoRoom/{bingoRoomId}/game";
        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (BusinessException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }
        return "redirect:/host/home";
    }

    @PostMapping("{bingoRoomIdstr}/finish")
    public String finish(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        return "redirect:/host/home";
    }

    @PostMapping("{bingoRoomIdstr}/delete")
    public String delete(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {
        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            bingoRoomService.delete(bingoRoomId);

        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }
        return "redirect:/host/home";
    }

}
