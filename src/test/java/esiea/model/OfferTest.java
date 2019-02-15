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
    	Receipt receipt = setCaracteristicsForTestOffer(3, SpecialOfferType.ThreeForTwo, 10.0);

        Double expectedResult = 2*0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testThreeForTwoOfferIfQuantityInferiorTo2(){
    	Receipt receipt = setCaracteristicsForTestOffer(1, SpecialOfferType.ThreeForTwo, 10.0);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
     
    @Test
    public void testTwoForAmountOffer(){
    	Receipt receipt = setCaracteristicsForTestOffer(4, SpecialOfferType.TwoForAmount, 0.99);

        Double expectedResult = (2*0.99);
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
	public void testTwoForAmountIfQuantityInferiorTo2 () {
    	Receipt receipt = setCaracteristicsForTestOffer(1, SpecialOfferType.TwoForAmount, 0.99);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
	}
    
    @Test
    public void testFiveForAmountOffer(){
    	Receipt receipt = setCaracteristicsForTestOffer(5, SpecialOfferType.FiveForAmount, 1.0);

        Double expectedResult = 1.0;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testFiveForAmountOfferIfQuantityInferiorTo5(){
    	Receipt receipt = setCaracteristicsForTestOffer(1, SpecialOfferType.FiveForAmount, 1.0);

        Double expectedResult = 0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    
    @Test
    public void testTenPercentDiscountOffer(){
    	Receipt receipt = setCaracteristicsForTestOffer(4, SpecialOfferType.TenPercentDiscount, 10.0);
    	
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
    
    private Receipt setCaracteristicsForTestOffer(double quantity, SpecialOfferType offerType, double argument) {
    	catalog.addProduct(toothbrush, 0.99);
        cart.addItemQuantity(toothbrush, quantity);
        teller.addSpecialOffer(offerType, toothbrush, argument);
        Receipt receipt = teller.checksOutArticlesFrom(cart);
        
        return receipt;
    }
}
