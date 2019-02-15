package esiea.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

	@Test
    public void testProductNameGetter () {
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		String name = toothbrush.getName();
		
		String expectedResult ="toothbrush";
		Assertions.assertEquals(expectedResult, name);
	}
	
	@Test
    public void testProductUnitGetter () {
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		ProductUnit unit = toothbrush.getUnit();
		
		ProductUnit expectedResult = ProductUnit.Each;
		Assertions.assertEquals(expectedResult, unit);
	}
}
