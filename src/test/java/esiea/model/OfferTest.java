package esiea.model;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OfferTest {

	private SupermarketCatalog catalog = new FakeCatalog();
    private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    private ShoppingCart cart = new ShoppingCart();
    private Teller teller = new Teller(catalog);
    
    @Test
    public void testApplePriceWithToothbrushDiscount() {
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        cart.addItemQuantity(apples, 2.5);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 1.99 * 2.5;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testThreeForTwoOffer(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 3);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 10.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 2*0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testThreeForTwoOfferIfQuantityInferiorTo2(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItem(toothbrush);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 10.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testTwoForAmountOffer(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 4);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, 0.99);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = (2*0.99);
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
	public void testTwoForAmountIfQuantityInferiorTo2 () {
		catalog.addProduct(toothbrush, 0.99);
        cart.addItem(toothbrush);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, 0.99);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
	}
    
    @Test
    public void testFiveForAmountOffer(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 5);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothbrush, 1.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 1.0;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testFiveForAmountOfferIfQuantityInferiorTo5(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItem(toothbrush);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothbrush, 1.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testTenPercentDiscountOffer(){
        catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 4);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = (4*0.99)-((4*0.99)*0.1);
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testDiscountIsNull () {
    	catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, 5);        
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        List<Discount> discounts = receipt.getDiscounts();

        Assertions.assertTrue(discounts.isEmpty());
    }
}
