package com.cbezmen.microservice.user.converter;

import com.cbezmen.microservice.user.client.AddressClient;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author canbezmen
 */
@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

    @Autowired
    private AddressClient addressClient;

    @Override
    public UserDTO convert(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        String address = addressClient.getAddress(user.getId());
        return UserDTO.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .age(user.getAge())
            .address(address)
            .build();
    }

}
