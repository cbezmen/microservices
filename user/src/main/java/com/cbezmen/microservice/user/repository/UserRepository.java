package com.cbezmen.microservice.user.repository;

import com.cbezmen.microservice.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author canbezmen
 */
public interface UserRepository extends MongoRepository<User, String> {
}
