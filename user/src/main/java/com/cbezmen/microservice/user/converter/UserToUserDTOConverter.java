package com.cbezmen.microservice.user.converter;

import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author canbezmen
 */
@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        if (Objects.isNull(source)) {
            return null;
        }
        return UserDTO.builder()
            .firstName(source.getFirstName())
            .lastName(source.getLastName())
            .age(source.getAge())
            .build();

    }
}
