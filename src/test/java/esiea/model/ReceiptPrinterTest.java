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

        ShoppingCart cart = new ShoppingCart();

        cart.addItemQuantity(toothbrush, 4);

        Teller teller = new Teller(catalog);
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        ReceiptPrinter receiptPrinter = new ReceiptPrinter();

        String stringToTest = receiptPrinter.printReceipt(receipt);
        String expectedString = "toothbrrush"+"                          "+" * "+"\n"+" "+"0.99"+" * "+"4"+"\n";


        Assertions.assertEquals(expectedString, stringToTest);
    }
}
