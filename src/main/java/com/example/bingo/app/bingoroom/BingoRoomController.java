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

/**
 * ビンゴルーム用 Controller
 * 
 * @author takuminv
 *
 */
@Controller
@RequestMapping("/host/bingoRoom")
public class BingoRoomController {

    /**
     * BingoRoomサービスクラス
     */
    @Inject
    BingoRoomService bingoRoomService;

    /**
     * FormとEntityのMapper
     */
    @Inject
    Mapper beanMapper;

    /**
     * BingoRoomフォームをmodelに設定
     * 
     * @return BingoRoomForm
     */
    @ModelAttribute
    public BingoRoomForm setUpForm() {
        BingoRoomForm form = new BingoRoomForm();
        return form;
    }

    /**
     * bingoRoomの情報を編集する
     * 
     * @param bingoRoomIdstr URLで指定したbingoRoomId
     * @param model
     * @param attributes
     * @return /hosyt/homeにリダイレクト
     */
    @GetMapping("{bingoRoomIdstr}/edit")
    public String edit(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        // URLに指定したbingoRoomIdに該当するbingoRoomを取得
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
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

    /**
     * bingoRoomの編集情報をDBに登録
     * 
     * @param bingoRoomIdstr URLに指定したbingoRoomId
     * @param form           BingoRoomForm
     * @param bindingResult
     * @param attributes
     * @return
     */
    @PostMapping("{bingoRoomIdstr}/edit")
    public String update(@PathVariable String bingoRoomIdstr,
            @Validated({ Default.class, BingoRoomCreate.class }) BingoRoomForm form, BindingResult bindingResult,
            RedirectAttributes attributes) {

        // バリデーションエラーがある場合はリダイレクト
        if (bindingResult.hasErrors()) {
            return "redirect:/host/home";
        }

        // URLに指定したbingoRoomIdに該当するbingoRoomを取得
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
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

    /**
     * bingoRoomを作成する
     * 
     * @return /bingoroom/create.jsp
     */
    @GetMapping("create")
    public String createForm() {

        return "/bingoroom/create";
    }

    /**
     * bingoRoomをDBに登録
     * 
     * @param form
     * @param bindingResult
     * @param userAccountDetails
     * @return
     */
    @PostMapping("create")
    public String create(@Validated({ Default.class, BingoRoomCreate.class }) BingoRoomForm form,
            BindingResult bindingResult, @AuthenticationPrincipal UserAccountDetails userAccountDetails) {

        // バリデーションエラーがある場合はリダイレクト
        if (bindingResult.hasErrors()) {
            return "redirect:/host/home";
        }

        // FormとBingoRoomをマッピング
        BingoRoom bingoRoom = beanMapper.map(form, BingoRoom.class);

        // bingoRoomをDBに登録
        bingoRoom.setCreateUserAccount(userAccountDetails.getUserAccount());
        bingoRoomService.create(bingoRoom);
        return "redirect:/host/home";
    }

    /**
     * 指定したBingoRoomのゲームを開始する
     * 
     * @param bingoRoomIdstr URLに指定したbingoRoomId
     * @param model
     * @param attributes
     * @return /host/homeにリダイレクト
     */
    @PostMapping("{bingoRoomIdstr}/start")
    public String start(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        // URLに指定したbingoRoomIdに該当するbingoRoomのゲームを開始し、ゲーム画面にリダイレクト
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
        // bingoRoomがすでに開始済みor終了ずみ → /host/homeにリダイレクト
        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            bingoRoomService.Start(bingoRoomId);
            return "redirect:/host/bingoRoom/" + bingoRoomId + "/bingoGame";
        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (BusinessException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }
        return "redirect:/host/home";
    }

    /**
     * 指定したbingoRoomのゲームを終了する
     * 
     * @param bingoRoomIdstr
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("{bingoRoomIdstr}/finish")
    public String finish(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        // URLに指定したbingoRoomIdに該当するbingoRoomのゲームを終了し、/host/homeにリダイレクト
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
        // bingoRoomがすでに開始前or終了ずみ → /host/homeにリダイレクト
        try {
            long bingoRoomId = Long.parseLong(bingoRoomIdstr);
            bingoRoomService.Finish(bingoRoomId);
        } catch (ResourceNotFoundException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (BusinessException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText(e.getMessage())));
        } catch (NumberFormatException e) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("Idが無効です。")));
        }
        return "redirect:/host/home";
    }

    /**
     * bingoRoomを削除する
     * 
     * @param bingoRoomIdstr URLに指定したbingoRoomId
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("{bingoRoomIdstr}/delete")
    public String delete(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        // URLに指定したbingoRoomIdに該当するbingoRoomを削除し、/host/homeにリダイレクト
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
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
