package com.cbezmen.microservice.user.controller;

import com.cbezmen.microservice.user.client.AddressClient;
import com.cbezmen.microservice.user.dto.UserDTO;
import com.cbezmen.microservice.user.model.User;
import com.cbezmen.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AddressClient addressClient;

    @GetMapping(value = {"/", ""})
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        UserDTO userDTO = userService.getUser(id);
        userDTO.setAddress(addressClient.getAddress(id));
        return userDTO;
    }

    @PutMapping(value = {"/", ""}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping(value = "/address")
    public String getAddress() {
        return addressClient.getAddress("1");
    }

}
