package com.cbezmen.microservice.user.client;

import com.cbezmen.microservice.user.client.fallback.AddressClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author canbezmen
 */
@FeignClient(name = "address", contextId = "addressClient", fallbackFactory = AddressClientFallbackFactory.class)
public interface AddressClient {

    @GetMapping("/address/{id}")
    String getAddress(@PathVariable(value = "id") String id);

}
