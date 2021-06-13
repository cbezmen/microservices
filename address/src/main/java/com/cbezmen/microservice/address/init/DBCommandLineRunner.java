package com.cbezmen.microservice.address.init;

import com.cbezmen.microservice.address.model.Address;
import com.cbezmen.microservice.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author canbezmen
 */
@Component
public class DBCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) {

        addressRepository.deleteAll();
        Address address = Address.builder()
            .id("1")
            .city("Istanbul")
            .build();
        addressRepository.save(address);

    }
}
