package esiea.model;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
	
	private ShoppingCart cart = new ShoppingCart();
	private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
	
	@Test
	public void testListItemsNotEmptyIfAddItem () {
		cart.addItem(toothbrush);
		List <ProductQuantity> listItems = cart.getItems();
		
		Assertions.assertNotNull(listItems);
	}
	
	@Test
	public void testProductQuantitiesNotEmptyIfAddItem() {
		cart.addItem(toothbrush);
		Map<Product, Double> productQuantities = cart.productQuantities();
		
		Assertions.assertNotNull(productQuantities);
	}
	
	@Test
	public void testAddItemQuantityIfItemNotInProductQuantities () {
		cart.addItem(toothbrush);
		Map<Product, Double> productQuantities = cart.productQuantities();
		double quantity = productQuantities.get(toothbrush);
		
		double expectedResult = 1.0;
		Assertions.assertEquals(expectedResult, quantity);
	}
	
	@Test
	public void testAddItemQuantityIfItemAlreadyInProductQuantities () {
		cart.addItem(toothbrush);
		cart.addItem(toothbrush);
		Map<Product, Double> productQuantities = cart.productQuantities();
		double quantity = productQuantities.get(toothbrush);
		
		double expectedResult = 2.0;
		Assertions.assertEquals(expectedResult, quantity);
	}
}
