package esiea.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackTest {
    private SupermarketCatalog catalog = new FakeCatalog();
    private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    private Product toothpaste = new Product("toothpaste", ProductUnit.Each);
    private ShoppingCart cart = new ShoppingCart();
    private Teller teller = new Teller(catalog);
    private Pack monPack = new Pack("myPack");

    @Test
    public void getterTest(){
        Map<Product, Double> maMap = new HashMap<>();
        Assertions.assertEquals(maMap, monPack.getPackContent());
    }
    @Test
    public void addPackItemIsNotNullIfAddItemTest(){
        monPack.addPackItem(toothbrush, 1.0);

        Assertions.assertNotNull(monPack);
    }
    @Test
    public void discountPackCalcTest(){
        catalog.addProduct(toothbrush, 2.0);
        catalog.addProduct(toothpaste, 3.0);


        monPack.addPackItem(toothbrush, 1.0);
        monPack.addPackItem(toothpaste, 1.0);

        double expectedDiscount = (2.0+3.0)*0.1;
        Discount myDiscount = monPack.calcPackOffer(catalog);
        Assertions.assertEquals(expectedDiscount, myDiscount.getDiscountAmount());
    }
    @Test
    public void getPackNameTest(){
        String expectedResult = "myPack";
        Assertions.assertEquals(expectedResult, monPack.getPackName());
    }
    @Test
    public void addNewPackTest(){
        teller.addNewPack(monPack);

        List<Pack> expectedList = new ArrayList<>();
        expectedList.add(monPack);
        Assertions.assertEquals(expectedList, teller.packs);
    }
}
