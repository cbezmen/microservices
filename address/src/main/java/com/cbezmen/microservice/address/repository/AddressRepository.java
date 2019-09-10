package com.cbezmen.microservice.address.repository;

import com.cbezmen.microservice.address.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author canbezmen
 */
public interface AddressRepository extends MongoRepository<Address, String> {
}
