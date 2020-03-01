package com.example.bingo.domain.model.bingoroom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.biingoroom.BingoRoomRepository;

/**
 * 
 * @author takuminv
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/bingo-infra.xml",
        "classpath:META-INF/spring/bingo-context.xml" })
@Transactional
public class BingoRoomEntityのバリデーションのテスト {

    @Inject
    BingoRoomRepository bingoRoomRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void バリデーションエラーなしのテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setStarted(true);
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        bingoRoomRepository.save(bingoRoom);

        assertTrue(true);

    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void roomNameのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName(null);
        bingoRoom.setStarted(true);
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        bingoRoomRepository.save(bingoRoom);
    }

    @Test
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void startedの指定がなければfalseになるテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        BingoRoom bingoRoom2 = bingoRoomRepository.save(bingoRoom);
        assertThat(bingoRoom2.isStarted(), is(false));
    }

    @Test
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void finishedの指定がなければfalseになるテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setStarted(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        BingoRoom bingoRoom2 = bingoRoomRepository.save(bingoRoom);
        assertThat(bingoRoom.isFinished(), is(false));
    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void createAtのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setStarted(true);
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(null);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        bingoRoomRepository.save(bingoRoom);
    }

    @Test(expected = ConstraintViolationException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void updatedAtのNotNull制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setStarted(true);
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(null);
        bingoRoom.setCreateUserAccount(userAccount);

        bingoRoomRepository.save(bingoRoom);
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    @Sql(scripts = "classpath:META-INF/sql/model/bingo/bingoResultId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void createUserAccountIdの参照性制約のテスト() {

        // DBに登録する日時情報の作成
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2020-01-20 21:07:22").toDate();

        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = this.createUserAccount();

        // idを変更し、DBに登録されていない情報にする
        userAccount.setUserId(1);

        BingoRoom bingoRoom = new BingoRoom();
        bingoRoom.setBingoRoomId(1);
        bingoRoom.setRoomName("test");
        bingoRoom.setStarted(true);
        bingoRoom.setFinished(true);
        bingoRoom.setCreatedAt(date);
        bingoRoom.setUpdatedAt(date);
        bingoRoom.setCreateUserAccount(userAccount);

        bingoRoomRepository.save(bingoRoom);
    }

    private UserAccount createUserAccount() {
        // BingoRoomの参照制約を満たすためのUserAccountデータの作成
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(3);
        userAccount.setPassword(
                "{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4");
        userAccount.setUserName("demo");

        return userAccount;
    }
}
