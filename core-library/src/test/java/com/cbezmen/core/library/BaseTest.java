package com.cbezmen.core.library;

import org.junit.Before;
import org.testng.annotations.BeforeTest;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author canbezmen
 */
public class BaseTest {
    @BeforeTest
    public void baseSetUp() {
        initMocks(this);
    }
}
