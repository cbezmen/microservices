package com.cbezmen.microservice.address.service.impl;

import com.cbezmen.microservice.address.model.Address;
import com.cbezmen.microservice.address.repository.AddressRepository;
import com.cbezmen.microservice.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author canbezmen
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getAddress(String id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            return addressOptional.get().getCity();
        }

        return "Address not found";
    }
}
