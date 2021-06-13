package com.cbezmen.microservice.user.client.fallback;

import com.cbezmen.microservice.user.client.AddressClient;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author canbezmen
 */
@ExtendWith(MockitoExtension.class)
class AddressClientFallbackFactoryTest {

    @InjectMocks
    private AddressClientFallbackFactory addressClientFallbackFactory;

    @Test
    void getAddressFallbackTest() {
        FeignException feignException = createFeignException(404, "Not Found", Request.HttpMethod.GET, "/address/1");
        AddressClient addressClient = addressClientFallbackFactory.create(new Throwable(feignException));
        String address = addressClient.getAddress("1");
        assertThat(address, is(""));
    }

    private FeignException createFeignException(int status, String reason, Request.HttpMethod method, String url) {
        return FeignException.errorStatus("some error", Response.builder()
            .status(status)
            .reason(reason)
            .request(Request.create(
                method,
                url,
                new HashMap<>(),
                null,
                null,
                null))
            .build());
    }

}