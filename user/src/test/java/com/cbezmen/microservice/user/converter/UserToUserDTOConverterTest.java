package com.cbezmen.microservice.user.converter;

import com.cbezmen.microservice.user.client.AddressClient;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

/**
 * @author canbezmen
 */
@ExtendWith(MockitoExtension.class)
class UserToUserDTOConverterTest {

    @InjectMocks
    private UserToUserDTOConverter userToUserDTOConverter;

    @Mock
    private AddressClient addressClient;

    @Test
    void convertNullTest() {
        UserDTO userDTO = userToUserDTOConverter.convert(null);
        assertThat(userDTO, is(nullValue()));
    }

    @Test
    void convertTest() {
        doReturn("Address").when(addressClient).getAddress(anyString());
        User user = new User("1", "Jane", "Doe", 20);
        UserDTO userDTO = userToUserDTOConverter.convert(user);
        assertThat(userDTO, allOf(
            hasProperty("firstName", is("Jane")),
            hasProperty("lastName", is("Doe")),
            hasProperty("age", is(20)),
            hasProperty("address", is("Address"))
        ));
    }
}