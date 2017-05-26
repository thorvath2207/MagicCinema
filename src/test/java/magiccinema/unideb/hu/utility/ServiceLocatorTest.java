package magiccinema.unideb.hu.utility;

import magiccinema.unideb.hu.services.interfaces.IService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServiceLocatorTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ServiceLocator.registerService(new TestService());
    }

    @Test
    public void getServiceTest() throws Exception {
        IService testService = ServiceLocator.getService("TestService");
        Assert.assertEquals(testService.getName(), "TestService");
    }

    @Test
    public void registerServiceTest() throws Exception {

    }
}