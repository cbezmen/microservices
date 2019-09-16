package com.cbezmen.microservice.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author canbezmen
 */
@FeignClient(name = "address", contextId = "addressClient")
public interface AddressClient {

    @GetMapping("/address/{id}")
    String getAddress(@PathVariable(value = "id") String id);

}
