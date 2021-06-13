package com.cbezmen.microservice.user.init;

import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author canbezmen
 */
@Component
public class DBCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        User user = User.builder()
            .id("1")
            .firstName("Can")
            .lastName("Bezmen")
            .age(29)
            .build();
        userRepository.save(user);
    }
}
