package com.cbezmen.microservice.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

/**
 * @author canbezmen
 */
@SpringBootTest
@Profile(value = {"!kubernetes"})
class UserApplicationTests {

    @Test
    void contextLoads() {
    }

}
