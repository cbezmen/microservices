package com.cbezmen.microservice.user.service.impl;

import com.cbezmen.microservice.user.converter.UserToUserDTOConverter;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.repository.UserRepository;
import com.cbezmen.microservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userToUserDTOConverter.convert(userOptional.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }
}
