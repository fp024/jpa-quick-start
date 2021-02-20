package org.fp024.jpaquick.shopping.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 설정이 올바른지 확인용도
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class SpringConfigurationTest {
    @Autowired
    private TransactionManager transactionManager;

    @Test
    void test() {
        assertNotNull(transactionManager);
    }
}