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
import com.example.bingo.domain.service.bingo.BingoResultService;
import com.example.bingo.domain.service.bingoroom.BingoRoomService;
import com.github.dozermapper.core.Mapper;

/**
 * ビンゴゲーム画面用 Controller
 * 
 * @author takuminv
 *
 */
@Controller
@RequestMapping("/host/bingoRoom/{bingoRoomIdstr}/bingoGame")
public class BingoContoller {

    /**
     * BingoRoomサービスクラス
     */
    @Inject
    BingoRoomService bingoRoomService;

    /**
     * Bingoサービスクラス
     */
    @Inject
    BingoResultService bingoService; //

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
    public BingoRoomForm setUpBingoRoomForm() {
        BingoRoomForm form = new BingoRoomForm();
        return form;
    }

    /**
     * Bingoフォームをmodelに設定
     * 
     * @return BingoForm
     */
    @ModelAttribute
    public BingoForm setUpBingoForm() {
        BingoForm form = new BingoForm();
        return form;
    }

    /**
     * ビンゴゲーム画面を返す bingoRoomIdが不正の場合、/host/homeにリダイレクト
     * ゲームが開始前または修理後の場合、/host/homeにリダイレクト
     * 
     * @param bingoRoomIdstr URLに指定したbingoRoomId
     * @param model
     * @param attributes
     * @return /bingogame/iindex.jsp
     */
    @GetMapping
    public String index(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes) {

        BingoRoom bingoRoom;

        // URLに指定したbingoRoomIdに該当するbingoRoomを取得
        // bingoRoomIdが不正(該当するbingoRoomが存在しない or 数字出ない) → /host/homeにリダイレクト
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

        // bingoRoomが開始前 or 終了後 → /host/homeにリダイレクト
        if (!bingoRoom.isStarted() || bingoRoom.isFinished()) {
            attributes.addFlashAttribute(ResultMessages.error().add(ResultMessage.fromText("ゲームが開始されていないか、終了しています。")));
            return "redirect:/host/home";
        }

        // 現在のBingoRoomで今までの抽選結果のリストを取得し、出た順(日時順)にソートする
        List<BingoResult> bingoResultList = bingoService.findAllByBingoRoom(bingoRoom).stream()
                .sorted(Comparator.comparing(BingoResult::getCreatedAt)).collect(Collectors.toList());

        // 抽選結果の候補を生成(テーブルで表示するために2次元配列とする)
        BingoCandidate[][] bingoCandidateList = new BingoCandidate[10][10];
        for (int bingoValue = 0; bingoValue < 100; bingoValue++) {
            bingoCandidateList[bingoValue / 10][bingoValue % 10] = new BingoCandidate(bingoValue + "", false);

            // 抽選結果として出ている値はResulted=trueとする
            for (BingoResult bingoResult : bingoResultList) {
                if (bingoResult.getBingoValue()
                        .equals(bingoCandidateList[bingoValue / 10][bingoValue % 10].getBingoValue())) {
                    bingoCandidateList[bingoValue / 10][bingoValue % 10].setResulted(true);
                }
            }
        }

        // modelに設定
        model.addAttribute("bingoRoom", bingoRoom);
        model.addAttribute("bingoResultList", bingoResultList);
        model.addAttribute("bingoCandiateList", bingoCandidateList);

        return "bingogame/index";
    }

    /**
     * POSTされた抽選結果の値をDBに登録する
     * 
     * @param bingoRoomIdstr URLに指定したbingoRoomId
     * @param model
     * @param attributes
     * @param form           BingoForm
     * @param bindingResult  Formのバリデーション結果
     * @return
     */
    @PostMapping("lottery")
    public String lottery(@PathVariable String bingoRoomIdstr, Model model, RedirectAttributes attributes,
            @Valid BingoForm form, BindingResult bindingResult) {

        // バリデーションエラーがある場合はリダイレクト
        if (bindingResult.hasErrors()) {
            return "redirect:/host/bingoRoom/" + bingoRoomIdstr + "/bingoGame";
        }

        // biingoRoomの今までの抽選結果を取得する
        BingoRoom bingoRoom = bingoRoomService.findByBingoRoomId(form.getBingoRoomId());
        List<BingoResult> bingoResultList = bingoService.findAllByBingoRoom(bingoRoom);

        // POSTされた抽選結果が今までの抽選結果と重複している場合はリダイレクト
        BingoResult bingoResult = beanMapper.map(form, BingoResult.class);
        if (bingoResultList.stream().anyMatch(result -> result.getBingoValue().equals(bingoResult.getBingoValue()))) {
            return "redirect:/host/bingoRoom/" + bingoRoomIdstr + "/bingoGame";
        }

        // 抽選結果をDBに登録
        bingoResult.setBingoRoom(bingoRoom);
        bingoService.create(bingoResult);

        return "redirect:/host/bingoRoom/" + bingoRoomIdstr + "/bingoGame";
    }
}
