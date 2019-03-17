import org.junit.Assert;
import org.junit.Test;
import service.extraService.Service;

import static org.junit.Assert.*;


public class ServiceTest {

    @Test
    public void additionTest(){
        assertEquals(2,Service.addition(1,1));
    }

    @Test
    public void concatenationTest(){
        Assert.assertEquals("ab",Service.concatenation("a","b"));
    }
}