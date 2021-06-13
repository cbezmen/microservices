package com.cbezmen.microservice.address.service.impl;

import com.cbezmen.microservice.address.model.Address;
import com.cbezmen.microservice.address.repository.AddressRepository;
import com.cbezmen.microservice.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author canbezmen
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getCity(String id) {
        return addressRepository.findById(id)
            .map(Address::getCity)
            .orElse("Address not found");

    }
}
