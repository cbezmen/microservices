package com.cbezmen.microservice.user.service.impl;

import com.cbezmen.microservice.user.client.AddressClient;
import com.cbezmen.microservice.user.converter.UserToUserDTOConverter;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author canbezmen
 */
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @SpyBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private UserToUserDTOConverter userToUserDTOConverter;

    @MockBean
    private AddressClient addressClient;

    @Test
    void getAllUserTest() {
        List<User> users = Collections.singletonList(new User("1", "Jane", "Doe", 20));
        doReturn(users).when(userRepository).findAll();
        doReturn("Address").when(addressClient).getAddress(anyString());

        List<UserDTO> userDTOList = userService.getAllUser();

        assertThat(userDTOList, hasSize(1));
        assertThat(userDTOList, contains(
            allOf(
                hasProperty("firstName", is("Jane")),
                hasProperty("lastName", is("Doe")),
                hasProperty("age", is(20)),
                hasProperty("address", is("Address"))
            )
        ));
        verify(userRepository, times(1)).findAll();
        verify(userToUserDTOConverter, times(1)).convert(ArgumentMatchers.any(User.class));
    }

    @Test
    void getUserValidTest() {
        User user = new User("1", "Jane", "Doe", 20);
        doReturn(Optional.of(user)).when(userRepository).findById(anyString());
        doReturn("Address").when(addressClient).getAddress(anyString());

        UserDTO userDTO = userService.getUser(anyString());
        assertThat(userDTO, allOf(
            hasProperty("firstName", is("Jane")),
            hasProperty("lastName", is("Doe")),
            hasProperty("age", is(20)),
            hasProperty("address", is("Address"))
        ));
        verify(userRepository, times(1)).findById(ArgumentMatchers.anyString());
        verify(userToUserDTOConverter, times(1)).convert(ArgumentMatchers.any(User.class));
    }

    @Test
    void getUserNotFoundTest() {
        doReturn(Optional.empty()).when(userRepository).findById(anyString());

        String id = "test-id";

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> userService.getUser(id),
            "Expected doThing() to throw, but it didn't"
        );

        assertThat(thrown.getMessage(), is("User not found for id " + id));
        verify(userToUserDTOConverter, times(0)).convert(ArgumentMatchers.any(User.class));
    }

}