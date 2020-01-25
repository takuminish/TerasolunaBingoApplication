package com.example.bingo.domain.repository.bingo;

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

import com.example.bingo.domain.model.BingoResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-infra.xml",
        "classpath:META-INF/spring/test-context.xml" })
@Transactional
public class BingoResultRepositoryのfindAllByBingoRoomBingoRoomIdのテスト {

    @Inject
    BingoResultRepository bingoResultRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/repository/bingo/findAllByBingoRoomBingoRoomId.sql", config = @SqlConfig(encoding = "utf-8"))
    public void 指定したBingoRoomIdに該当するBingoResultが1件以上存在する場合のテスト() {

        // 結果照合用データ作成
        List<BingoResult> TestBingoResultList = new ArrayList<BingoResult>();
        BingoResult TestBingoResult1 = new BingoResult();
        TestBingoResult1.setBingoId(11);
        TestBingoResultList.add(TestBingoResult1);
        BingoResult TestBingoResult2 = new BingoResult();
        TestBingoResult2.setBingoId(12);
        TestBingoResultList.add(TestBingoResult2);

        // テスト対象メソッド実行
        List<BingoResult> bingoResultList = bingoResultRepository.findAllByBingoRoomBingoRoomId(10);

        // 結果確認
        assertThat(bingoResultList.size(), is(TestBingoResultList.size()));
        assertThat(bingoResultList.get(0).getBingoId(), is(TestBingoResult1.getBingoId()));
        assertThat(bingoResultList.get(1).getBingoId(), is(TestBingoResult2.getBingoId()));

    }
}
