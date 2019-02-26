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
    private Teller teller = new Teller(catalog);
    private Pack monPack = new Pack("myPack");

    @Test
    public void testGetPackContent(){
        Map<Product, Double> expectedMap = new HashMap<>();
        Assertions.assertEquals(expectedMap, monPack.getPackContent());
    }
    
    @Test
    public void testAddPackItemIsNotNullIfAddItem(){
        monPack.addPackItem(toothbrush, 1.0);

        Assertions.assertNotNull(monPack);
    }
    
    @Test
    public void testDiscountPackCalc(){
        catalog.addProduct(toothbrush, 2.0);
        catalog.addProduct(toothpaste, 3.0);

        monPack.addPackItem(toothbrush, 1.0);
        monPack.addPackItem(toothpaste, 1.0);

        double expectedSum = 2.0+3.0;
        double mySum = monPack.calcPackOffer(catalog);
        Assertions.assertEquals(expectedSum, mySum);
    }
    
    @Test
    public void testGetPackName(){
        String expectedResult = "myPack";
        Assertions.assertEquals(expectedResult, monPack.getPackName());
    }
    
    @Test
    public void testAddNewPack(){
        teller.addNewPack(monPack);

        List<Pack> expectedList = new ArrayList<>();
        expectedList.add(monPack);
        Assertions.assertEquals(expectedList, teller.packs);
    }
}
