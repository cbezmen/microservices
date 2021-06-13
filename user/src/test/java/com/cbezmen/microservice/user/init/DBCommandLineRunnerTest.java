package com.cbezmen.microservice.user.init;

import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author canbezmen
 */
@ExtendWith(MockitoExtension.class)
class DBCommandLineRunnerTest {

    @InjectMocks
    private DBCommandLineRunner dbCommandLineRunner;

    @Mock
    private UserRepository userRepository;

    @Test
    public void runTest() {
        dbCommandLineRunner.run();
        verify(userRepository, times(1)).deleteAll();
        verify(userRepository, times(1)).save(any(User.class));
    }
}