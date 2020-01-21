package com.example.bingo.domain.service.bingo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bingo.domain.model.BingoResult;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.repository.bingo.BingoResultRepository;

/**
 * ビンゴゲーム用サービスの実装クラス
 * 
 * @author takuminv
 *
 */
@Service
@Transactional
public class BingoResultServiceImpl implements BingoResultService {

    /**
     * BingoResult Entity用 Repository
     */
    @Inject
    BingoResultRepository bingoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public BingoResult create(BingoResult bingoResult) {
        bingoResult.setCreatedAt(new Date());
        bingoRepository.save(bingoResult);
        return bingoResult;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<BingoResult> findAllByBingoRoom(BingoRoom bingoRoom) {
        List<BingoResult> bingoList = bingoRepository.findAllByBingoRoomBingoRoomId(bingoRoom.getBingoRoomId());
        return bingoList;
    }

}
