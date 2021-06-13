package com.cbezmen.microservice.user.service.impl;

import com.cbezmen.microservice.user.converter.UserToUserDTOConverter;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.repository.UserRepository;
import com.cbezmen.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author canbezmen
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserToUserDTOConverter userToUserDTOConverter;


    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream()
            .map(userToUserDTOConverter::convert)
            .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(String id) {
        return userRepository.findById(id)
            .map(userToUserDTOConverter::convert)
            .orElseThrow(() -> new IllegalArgumentException("User not found for id " + id));
    }

    
}
