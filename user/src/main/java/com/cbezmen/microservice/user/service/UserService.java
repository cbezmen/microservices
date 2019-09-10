package com.cbezmen.microservice.user.service;

import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;

import java.util.List;

/**
 * @author canbezmen
 */
public interface UserService {

    List<User> getAllUser();

    UserDTO getUser(String id);

    User addNewUser(User user);
}
