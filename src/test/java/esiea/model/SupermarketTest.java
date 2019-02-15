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

        double expected_price = 0.99;
        Assertions.assertEquals(expected_price, catalog.getUnitPrice(toothbrush));
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

        Double Expected_result = 1.99 * 2.5;
        Assertions.assertEquals(Expected_result, receipt.getTotalPrice());
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

        Double Expected_result = 2*0.99;
        Assertions.assertEquals(Expected_result, receipt.getTotalPrice());
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

        Double Expected_result = (2*0.99);
        Assertions.assertEquals(Expected_result, receipt.getTotalPrice());
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

        Double Expected_result = 1.0;
        Assertions.assertEquals(Expected_result, receipt.getTotalPrice());
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

        Double Expected_result = (4*0.99)-((4*0.99)*0.1);
        Assertions.assertEquals(Expected_result, receipt.getTotalPrice());
    }
    @Test
    public void testOfferGetter(){
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        Offer My_offer = new Offer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);

        Product Expected_result = toothbrush;
        Assertions.assertEquals(Expected_result, My_offer.getProduct());
    }
    @Test
    public void testReceiptGetItems(){
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<ReceiptItem> Expected_tab = new ArrayList();
        Assertions.assertEquals(Expected_tab, receipt.getItems());
    }
    @Test
    public void testReceiptGetDiscounts(){
        SupermarketCatalog catalog = new FakeCatalog();
        ShoppingCart cart = new ShoppingCart();
        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        List<Discount> Expected_tab = new ArrayList<>();
        Assertions.assertEquals(Expected_tab, receipt.getDiscounts());
    }

}
