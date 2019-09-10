package com.cbezmen.microservice.user.service.impl;

import com.cbezmen.core.library.BaseTest;
import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author canbezmen
 */
public class UserServiceImplTest extends BaseTest {

    List<User> userList;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeTest
    public void setUp() {
        User user1 = new User("Can", "Bezmen", 26);
        User user2 = new User("Cem", "Bezmen", 26);
        userList = new ArrayList<>();
        userList.addAll(Arrays.asList(user1, user2));
    }


    @Test(timeOut = 100)
    public void getAllUser() throws InterruptedException {
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals("Must be equal", userService.getAllUser().size(), userList.size());

        verify(userRepository, times(1)).findAll();
    }
}