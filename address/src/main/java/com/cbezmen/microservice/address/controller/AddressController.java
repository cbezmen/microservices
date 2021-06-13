package com.cbezmen.microservice.address.controller;

import com.cbezmen.microservice.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author canbezmen
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}")
    public String getAddress(@PathVariable String id) {
        return addressService.getCity(id);
    }
}
