package com.cbezmen.microservice.address;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

/**
 * @author canbezmen
 */
@SpringBootTest
@Profile(value = {"!kubernetes"})
class AddressApplicationTests {

    @Test
    void contextLoads() {
    }

}