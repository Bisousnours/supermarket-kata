package esiea.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SupermarketTest {

	private SupermarketCatalog catalog = new FakeCatalog();
    private Product toothbrush = new Product("toothbrush", ProductUnit.Each);

    @Test
    public void testProductPriceInCatalog(){
        catalog.addProduct(toothbrush, 0.99);

        double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, catalog.getUnitPrice(toothbrush));
    }
    
}
