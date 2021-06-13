package com.cbezmen.microservice.user.service;

import com.cbezmen.microservice.user.dto.UserDTO;

import java.util.List;

/**
 * @author canbezmen
 */
public interface UserService {

    List<UserDTO> getAllUser();

    UserDTO getUser(String id);

}
