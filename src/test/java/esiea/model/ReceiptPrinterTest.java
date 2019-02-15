package esiea.model;


import esiea.ReceiptPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class ReceiptPrinterTest {

    @Test
    public void testPrintReceipt(){
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 0.90);

        ShoppingCart cart = new ShoppingCart();

        cart.addItemQuantity(toothbrush, 4);
        cart.addItemQuantity(toothpaste, 1);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter receiptPrinter = new ReceiptPrinter();

        String stringToTest = receiptPrinter.printReceipt(receipt);
        String expectedString = "toothbrush"+"                          "+" * "+"\n"+" "+"0.99"+" * "+"4"+"\n"+"toothpaste"+"                          "+" * "+"\n";


        Assertions.assertEquals(expectedString, stringToTest);
    }
}
