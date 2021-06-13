package com.cbezmen.microservice.user.controller;

import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author canbezmen
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = {"/", ""})
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUser(id);
    }


}
