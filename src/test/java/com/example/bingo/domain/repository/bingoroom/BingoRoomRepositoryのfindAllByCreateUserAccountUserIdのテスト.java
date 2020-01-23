package com.example.bingo.domain.repository.bingoroom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.bingo.domain.model.BingoRoom;
import com.example.bingo.domain.model.UserAccount;
import com.example.bingo.domain.repository.biingoroom.BingoRoomRepository;

/**
 * BingoRoomRepositoryクラスのテスト
 * 
 * @author takuminv
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-infra.xml",
        "classpath:META-INF/spring/test-context.xml" })
@Transactional
public class BingoRoomRepositoryのfindAllByCreateUserAccountUserIdのテスト {

    @Inject
    BingoRoomRepository bingoRoomRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/repository/bingoroom/findAllByCreateUserAccountUserId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void 正常系() {

        // 結果照合用データ作成
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(3);
        userAccount.setPassword(
                "{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4");
        userAccount.setUserName("demo");
        List<BingoRoom> TestBingoRoomList = new ArrayList<BingoRoom>();

        BingoRoom TestBingoRoom1 = new BingoRoom();
        TestBingoRoom1.setBingoRoomId(10);
        TestBingoRoomList.add(TestBingoRoom1);

        BingoRoom TestBingoRoom2 = new BingoRoom();
        TestBingoRoom2.setBingoRoomId(11);
        TestBingoRoomList.add(TestBingoRoom2);

        BingoRoom TestBingoRoom3 = new BingoRoom();
        TestBingoRoom3.setBingoRoomId(12);
        TestBingoRoomList.add(TestBingoRoom3);

        // テスト対象メソッド実行
        List<BingoRoom> bingoRoomList = bingoRoomRepository.findAllByCreateUserAccountUserId(3);

        // 結果確認
        assertThat(bingoRoomList.size(), is(3));
        assertThat(bingoRoomList.get(0).getBingoRoomId(), is(TestBingoRoom1.getBingoRoomId()));
        assertThat(bingoRoomList.get(1).getBingoRoomId(), is(TestBingoRoom2.getBingoRoomId()));
        assertThat(bingoRoomList.get(2).getBingoRoomId(), is(TestBingoRoom3.getBingoRoomId()));
    }
}
