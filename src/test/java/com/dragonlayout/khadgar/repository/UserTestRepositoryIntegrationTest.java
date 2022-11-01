package com.dragonlayout.khadgar.repository;

import com.dragonlayout.khadgar.config.JpaAuditingConfiguration;
import com.dragonlayout.khadgar.model.UserTestDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfiguration.class)
public class UserTestRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserTestDoRepository userTestDoRepository;

    @Test
    public void givenUser_whenPersist_returnOperationSuccessful() {
        // given
        UserTestDo userTestDo = new UserTestDo();
        userTestDo.setUsername("chenlong");
        userTestDo.setPassword("123456");
        entityManager.persist(userTestDo);
        UserTestDo u = userTestDoRepository.findUserTestDoByUsername("chenlong");
        // when
        // then
        Assertions.assertEquals(userTestDo.getCreateTime(), u.getCreateTime());
        Assertions.assertEquals(userTestDo.getUpdateTime(), u.getUpdateTime());
        Assertions.assertEquals(userTestDo.getSex(), u.getSex());
    }
}
