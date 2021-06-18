package com.cbezmen.microservice.user.client.fallback;

import com.cbezmen.microservice.user.client.AddressClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author cbezmen
 */
@Component
@Slf4j
public class AddressClientFallbackFactory implements FallbackFactory<AddressClient> {
    @Override
    public AddressClient create(Throwable cause) {
        return new AddressClient() {
            @Override
            public String getAddress(String id) {
                if (cause instanceof FeignException) {
                    FeignException feignException = (FeignException) cause.getCause();
                    log.warn("Can't access address client status {} message {}", feignException.status(), feignException.getMessage());
                } else {
                    log.warn("Message: {}", cause.getCause().getMessage());
                }
                return "";
            }
        };
    }
}
