package esiea.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SupermarketTest {

    @Test
    public void testProductPriceInCatalog(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        double expectedPrice = 0.99;
        Assertions.assertEquals(expectedPrice, catalog.getUnitPrice(toothbrush));
    }
    @Test
    public void testApplePriceWithToothbrushDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 1.99 * 2.5;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    @Test
    public void testThreeForTwoOffer(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 2*0.99;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    @Test
    public void testTwoForAmountOffer(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 4);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TwoForAmount, toothbrush, 0.99);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = (2*0.99);
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    @Test
    public void testFiveForAmountOffer(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 5);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FiveForAmount, toothbrush, 1.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = 1.0;
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    @Test
    public void testTenPercentDiscountOffer(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 4);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Receipt receipt = teller.checksOutArticlesFrom(cart);

        Double expectedResult = (4*0.99)-((4*0.99)*0.1);
        Assertions.assertEquals(expectedResult, receipt.getTotalPrice());
    }
    @Test
    public void testOfferGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Offer My_offer = new Offer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Product expectedResult = toothbrush;
        Assertions.assertEquals(expectedResult, My_offer.getProduct());
    }
    @Test
    public void testReceiptGetItems(){
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<ReceiptItem> expectedTab = new ArrayList();
        Assertions.assertEquals(expectedTab, receipt.getItems());
    }
    @Test
    public void testReceiptGetDiscounts(){
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<Discount> expectedTab = new ArrayList<>();
        Assertions.assertEquals(expectedTab, receipt.getDiscounts());
    }
    @Test
    public void testDiscountProductGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Discount discount = new Discount(toothbrush, "my description", 3);

        Product expectedProduct = toothbrush;
        Assertions.assertEquals(expectedProduct, discount.getProduct());
    }
    @Test
    public void testDiscountDescriptionGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Discount discount = new Discount(toothbrush, "my description", 3);

        String expectedDescription = "my description";
        Assertions.assertEquals(expectedDescription, discount.getDescription());
    }
    @Test
    public void testDiscountAmountGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Discount discount = new Discount(toothbrush, "my description", 3);

        double expectedDiscountAmount = 3;
        Assertions.assertEquals(expectedDiscountAmount, discount.getDiscountAmount());
    }
    @Test
    public void testReceiptItemPriceGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedPrice = 3;
        Assertions.assertEquals(expectedPrice, item.getPrice());
    }
    @Test
    public void testReceiptItemProductGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        Product expectedProduct = toothbrush;
        Assertions.assertEquals(expectedProduct, item.getProduct());
    }
    @Test
    public void testReceiptItemQuantityGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedQuantity = 2;
        Assertions.assertEquals(expectedQuantity, item.getQuantity());
    }
    @Test
    public void testReceiptItemTotalPriceyGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        ReceiptItem item = new ReceiptItem(toothbrush, 2, 3, 6);

        double expectedTotalPrice = 6;
        Assertions.assertEquals(expectedTotalPrice, item.getTotalPrice());
    }
}
