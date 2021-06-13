package com.cbezmen.microservice.address.service.impl;

import com.cbezmen.microservice.address.model.Address;
import com.cbezmen.microservice.address.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

/**
 * @author canbezmen
 */
@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void getCityNotFoundTest() {
        doReturn(Optional.empty()).when(addressRepository).findById(anyString());
        String city = addressService.getCity("1");
        assertThat(city, is("Address not found"));
    }

    @Test
    void getCityTest() {
        Address address = Address.builder().id("1").city("istanbul").build();
        doReturn(Optional.of(address)).when(addressRepository).findById(anyString());
        String city = addressService.getCity("1");
        assertThat(city, is(address.getCity()));
    }
}