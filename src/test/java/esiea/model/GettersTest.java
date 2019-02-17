package esiea.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GettersTest {

	private SupermarketCatalog catalog = new FakeCatalog();
    private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    private ShoppingCart cart = new ShoppingCart();
    private Teller teller = new Teller(catalog);

    @Test
    public void testTwoForAmountOfferGetter(){
        Offer offer = new TwoForAmount(SpecialOfferType.TwoForAmount, toothbrush, 10.0);

        Product expectedResult = toothbrush;
        Assertions.assertEquals(expectedResult, offer.getProduct());
    }

    @Test
    public void testTenPercentDiscountOfferGetter(){
        Offer offer = new TenPercentDiscount(SpecialOfferType.TenPercentDiscount, toothbrush, 2);

        Product expectedResult = toothbrush;
        Assertions.assertEquals(expectedResult, offer.getProduct());
    }

    @Test
    public void testThreeForTwoOfferGetter(){
        Offer offer = new TenPercentDiscount(SpecialOfferType.ThreeForTwo, toothbrush, 2);

        Product expectedResult = toothbrush;
        Assertions.assertEquals(expectedResult, offer.getProduct());
    }

    @Test
    public void testFiveForAmountOfferGetter(){
        Offer offer = new TenPercentDiscount(SpecialOfferType.FiveForAmount, toothbrush, 2);

        Product expectedResult = toothbrush;
        Assertions.assertEquals(expectedResult, offer.getProduct());
    }
    
    @Test
    public void testReceiptGetItems(){
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<ReceiptItem> expectedTab = new ArrayList<>();
        Assertions.assertEquals(expectedTab, receipt.getItems());
    }
    
    @Test
    public void testReceiptGetDiscounts(){
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<Discount> expectedTab = new ArrayList<>();
        Assertions.assertEquals(expectedTab, receipt.getDiscounts());
    }
    
    @Test
    public void testDiscountProductGetter(){
        Discount discount = new Discount(toothbrush, "my description", 3);

        Product expectedProduct = toothbrush;
        Assertions.assertEquals(expectedProduct, discount.getProduct());
    }
    
    @Test
    public void testDiscountDescriptionGetter(){
        Discount discount = new Discount(toothbrush, "my description", 3);

        String expectedDescription = "my description";
        Assertions.assertEquals(expectedDescription, discount.getDescription());
    }
    
    @Test
    public void testDiscountAmountGetter(){
        Discount discount = new Discount(toothbrush, "my description", 3);

        double expectedDiscountAmount = 3;
        Assertions.assertEquals(expectedDiscountAmount, discount.getDiscountAmount());
    }
    
    @Test
    public void testReceiptItemPriceGetter(){
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedPrice = 3;
        Assertions.assertEquals(expectedPrice, item.getPrice());
    }
    
    @Test
    public void testReceiptItemProductGetter(){
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        Product expectedProduct = toothbrush;
        Assertions.assertEquals(expectedProduct, item.getProduct());
    }
    
    @Test
    public void testReceiptItemQuantityGetter(){
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedQuantity = 2;
        Assertions.assertEquals(expectedQuantity, item.getQuantity());
    }
    
    @Test
    public void testReceiptItemTotalPriceyGetter(){
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedTotalPrice = 6;
        Assertions.assertEquals(expectedTotalPrice, item.getTotalPrice());
    }
    
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
