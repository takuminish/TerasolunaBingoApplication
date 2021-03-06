package com.example.bingo.domain.service.bingoroom;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.biingoroom.BingoRoomRepository;

/**
 * ビンゴルーム用 サービス実装クラス
 * 
 * @author takuminv
 *
 */
@Service
@Transactional
public class BingoRoomServiceImpl implements BingoRoomService {

    /**
     * BingoRoom Entity用 Repository
     */
    @Inject
    BingoRoomRepository bingoRoomRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<BingoRoom> findAllByCreateUser(UserAccount userAccount) {
        List<BingoRoom> bingoRoomList = bingoRoomRepository.findAllByCreateUserAccountUserId(userAccount.getUserId());
        return bingoRoomList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BingoRoom findByBingoRoomId(long bingoRoomId) {
        BingoRoom bingoRoom = bingoRoomRepository.findById(bingoRoomId).orElse(null);

        // BingoRoomが見つからなかったらException
        if (bingoRoom == null) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E404] BingoRoom is NotFound"));
            throw new ResourceNotFoundException(messages);
        }
        return bingoRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BingoRoom create(BingoRoom bingoRoom) {
        bingoRoom.setStarted(false);
        bingoRoom.setStarted(false);
        bingoRoom.setCreatedAt(new Date());
        bingoRoom.setUpdatedAt(new Date());
        bingoRoomRepository.save(bingoRoom);
        return bingoRoom;
    }

    /**
     * {@inheritDoc}
     */
    public BingoRoom update(BingoRoom bingoRoom) {

        BingoRoom updatedBingoRoom = this.findByBingoRoomId(bingoRoom.getBingoRoomId());
        updatedBingoRoom.setRoomName(bingoRoom.getRoomName());
        return bingoRoom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BingoRoom Start(Long bingoRoomId) {
        BingoRoom bingoRoom = this.findByBingoRoomId(bingoRoomId);

        // ビンゴがすでに始まっているor終わっている時はエラー
        if (bingoRoom.isStarted() || bingoRoom.isFinished()) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E002] BingoRoom is already started or finished"));
            throw new BusinessException(messages);
        }

        bingoRoom.setStarted(true);

        return this.update(bingoRoom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BingoRoom Finish(Long bingoRoomId) {
        BingoRoom bingoRoom = this.findByBingoRoomId(bingoRoomId);

        // ビンゴがまだ始まっていないor終わっている時はエラー
        if (!bingoRoom.isStarted() || bingoRoom.isFinished()) {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E002] BingoRoom is already started or finished"));
            throw new BusinessException(messages);
        }

        bingoRoom.setFinished(true);

        return this.update(bingoRoom);
    }

    @Override
    public void delete(long bingoRoomId) {
        BingoRoom bingoRoom = this.findByBingoRoomId(bingoRoomId);
        bingoRoomRepository.delete(bingoRoom);
    }

}
