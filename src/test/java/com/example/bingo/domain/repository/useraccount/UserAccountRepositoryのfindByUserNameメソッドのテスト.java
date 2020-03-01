package com.example.bingo.domain.repository.useraccount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.bingo.domain.model.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/bingo-infra.xml",
        "classpath:META-INF/spring/bingo-context.xml" })
@Transactional
public class UserAccountRepositoryのfindByUserNameメソッドのテスト {

    @Inject
    UserAccountRepository userAccountRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/repository/useraccount/findByUserName.sql", config = @SqlConfig(encoding = "utf-8"))
    public void 指定したUserNameに該当するUserAccountが存在する場合のテスト() {

        // 結果照合用データ作成
        UserAccount TestUserAccount1 = new UserAccount();
        TestUserAccount1.setUserId(3);
        UserAccount TestUserAccount2 = new UserAccount();
        TestUserAccount2.setUserId(4);
        UserAccount TestUserAccount3 = new UserAccount();
        TestUserAccount3.setUserId(5);
        UserAccount TestUserAccount4 = new UserAccount();
        TestUserAccount4.setUserId(6);

        // テスト対象メソッド実行
        UserAccount userAccount1 = userAccountRepository.findByUserName("demo1").orElse(null);
        UserAccount userAccount2 = userAccountRepository.findByUserName("demo2").orElse(null);
        UserAccount userAccount3 = userAccountRepository.findByUserName("demo3").orElse(null);
        UserAccount userAccount4 = userAccountRepository.findByUserName("demo4").orElse(null);

        // 結果確認
        assertThat(userAccount1.getUserId(), is(TestUserAccount1.getUserId()));
        assertThat(userAccount2.getUserId(), is(TestUserAccount2.getUserId()));
        assertThat(userAccount3.getUserId(), is(TestUserAccount3.getUserId()));
        assertThat(userAccount4.getUserId(), is(TestUserAccount4.getUserId()));

    }
}
