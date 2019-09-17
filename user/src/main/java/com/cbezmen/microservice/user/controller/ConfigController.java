package com.cbezmen.microservice.user.controller;

import com.cbezmen.core.library.config.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author canbezmen
 */
@RestController
public class ConfigController {

    @Autowired
    private MessageProperties messageProperties;

    @GetMapping(value = "/config")
    public String getMessage() {
        String hello = messageProperties.getHello();
        if (hello == null) {
            return "this is null";
        }
        return hello;
    }
}
