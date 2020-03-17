package za.co.mtn.product;

import org.junit.Test;

import static org.junit.Assert.fail;

public class ProductSystemServiceTest {

    @Test
    public void testMain() {
        try {
            ProductSystemService.main(new String[0]);
        } catch (Exception e) {
            fail("No exceptions should occur on startup.");
        }
    }

}
