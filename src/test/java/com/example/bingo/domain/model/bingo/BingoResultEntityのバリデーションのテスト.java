package com.example.bingo.domain.model.bingo;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.bingo.domain.model.BingoResult;
import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.bingo.BingoResultRepository;

/**
 * BingoResultEntityのバリデーションのテスト
 * 
 * @author takuminv
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-infra.xml",
        "classpath:META-INF/spring/test-context.xml" })
@Transactional
public class BingoResultEntityのバリデーションのテスト {

    @Inject
    BingoResultRepository bingoResultRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void バリデーションエラーなしのテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        BingoRoom bingoRoom = createBingoRoom(date);

        // 正しくデータ登録
        BingoResult bingoResult = new BingoResult();
        bingoResult.setBingoId(11);
        bingoResult.setBingoValue("11");
        bingoResult.setBingoRoom(bingoRoom);
        bingoResult.setCreatedAt(date);

        // 登録が成功することを確認する
        bingoResultRepository.save(bingoResult);
        assertTrue(true);
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void BingoRoomIdの参照性制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomデータ作成(参照性制約のため)
        // DBへの事前登録を行なっていないため、存在しないBingoRoomを作成したことになる
        BingoRoom TestBingoRoom = createBingoRoom(date);

        // 登録が失敗することを確認する
        BingoResult TestBingoResult1 = new BingoResult();
        TestBingoResult1.setBingoId(11);
        TestBingoResult1.setBingoValue("11");
        TestBingoResult1.setBingoRoom(TestBingoRoom);
        TestBingoResult1.setCreatedAt(date);
        BingoResult bingoResult1 = bingoResultRepository.save(TestBingoResult1);
    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void BingoRoomIdのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomのみnullで作成
        BingoResult TestBingoResult1 = new BingoResult();
        TestBingoResult1.setBingoId(11);
        TestBingoResult1.setBingoValue("11");
        TestBingoResult1.setBingoRoom(null);
        TestBingoResult1.setCreatedAt(date);

        // 登録が失敗することを確認する
        BingoResult bingoResult1 = bingoResultRepository.save(TestBingoResult1);
    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void BingoValueのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomデータ作成(参照性制約のため)
        BingoRoom TestBingoRoom = createBingoRoom(date);

        // BingoValueのみnullで作成
        BingoResult TestBingoResult1 = new BingoResult();
        TestBingoResult1.setBingoId(11);
        TestBingoResult1.setBingoValue(null);
        TestBingoResult1.setBingoRoom(TestBingoRoom);
        TestBingoResult1.setCreatedAt(date);

        // 登録が失敗することを確認する
        BingoResult bingoResult1 = bingoResultRepository.save(TestBingoResult1);
    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void CreatedAtのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomデータ作成(参照性制約のため)
        BingoRoom TestBingoRoom = createBingoRoom(date);

        // CreatedAtのみnullで作成
        BingoResult TestBingoResult1 = new BingoResult();
        TestBingoResult1.setBingoId(11);
        TestBingoResult1.setBingoValue("11");
        TestBingoResult1.setBingoRoom(TestBingoRoom);
        TestBingoResult1.setCreatedAt(null);

        // 登録が失敗することを確認する
        BingoResult bingoResult1 = bingoResultRepository.save(TestBingoResult1);
    }

    /**
     * 参照制約を満たすためのBingoRoomの作成
     * 
     * @param date DBに登録する用の日時
     * @return 参照条件を満たすためのBingoRoom
     */
    private BingoRoom createBingoRoom(Date date) {

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(3);
        userAccount.setPassword(
                "{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4");
        userAccount.setUserName("demo");

        // BingoRoomデータの作成
        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(10);
        bingoRoom.setRoomName("test1");
        bingoRoom.setStarted(false);
        bingoRoom.setFinished(false);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        return bingoRoom;

    }
}