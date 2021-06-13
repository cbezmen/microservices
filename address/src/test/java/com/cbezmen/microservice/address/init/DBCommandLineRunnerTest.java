package com.cbezmen.microservice.address.init;

import com.cbezmen.microservice.address.model.Address;
import com.cbezmen.microservice.address.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author canbezmen
 */
@ExtendWith(MockitoExtension.class)
class DBCommandLineRunnerTest {

    @InjectMocks
    private DBCommandLineRunner dbCommandLineRunner;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void runTest() {
        dbCommandLineRunner.run();
        verify(addressRepository, times(1)).deleteAll();
        verify(addressRepository, times(1)).save(any(Address.class));
    }
}