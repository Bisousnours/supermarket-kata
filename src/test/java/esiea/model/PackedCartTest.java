package esiea.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PackedCartTest {
	
	private PackedCart packedCart = new PackedCart();
	private SupermarketCatalog catalog = new FakeCatalog();
    private Product toothbrush = new Product("toothbrush", ProductUnit.Each);
    private Product toothpaste = new Product("toothpaste", ProductUnit.Each);
    private Teller teller = new Teller(catalog);
    private Pack pack = new Pack("myPack");
    private ShoppingCart cart = new ShoppingCart();
    
    public PackedCartTest() {
    	catalog.addProduct(toothbrush, 2.0);
        catalog.addProduct(toothpaste, 3.0);

        pack.addPackItem(toothbrush, 1.0);
        pack.addPackItem(toothpaste, 1.0);
        
        cart.addItem(toothbrush);
        cart.addItem(toothpaste);
        
        teller.addNewPack(pack);

        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
	} 
	
    @Test
    public void testGetPackElementFound() {
    	int expectedResult = 0;
		Assertions.assertEquals(expectedResult, packedCart.getPackElementFound());
	}
    
    @Test
    public void testGetPacksFoundAndSumPrice() {
		Map<Product, Double> expectedMap = new HashMap<Product, Double>();
		Assertions.assertEquals(expectedMap, packedCart.getPacksFoundAndSumPrice());
	}
	
	@Test
    public void testFindPackElements() {
        packedCart.findPackElements(pack.getPackContent(), cart.getItems());
        
        int expectedElementsFound = 2;
        Assertions.assertEquals(expectedElementsFound, packedCart.getPackElementFound());
	}
	
	@Test
	public void testReplacePackElementsByPackAsOneProduct () {
		List<ProductQuantity> productQuantities = packedCart.replacePackElementsByPackAsOneProduct(pack, cart.getItems(), catalog);
		
		String expectedName = "myPack";
		Assertions.assertEquals(expectedName, productQuantities.get(0).getProduct().getName());
	}
	
	@Test
	public void testHandlePacks() {
		List<ProductQuantity> productQuantities = packedCart.handlePacks(cart, teller.packs, catalog);
		
		int expectedSize = 1;
		Assertions.assertEquals(expectedSize, productQuantities.size());
	}
	
	@Test
	public void testHandlePacksIfNoPack() {
		ShoppingCart cartNoPack = new ShoppingCart();
		Product orange = new Product("orange", ProductUnit.Kilo);
		Product lemon = new Product("lemon", ProductUnit.Kilo);
		cartNoPack.addItem(orange);
		cartNoPack.addItem(lemon);
		List<ProductQuantity> productQuantities = packedCart.handlePacks(cartNoPack, teller.packs, catalog);
		
		int expectedSize = 2;
		Assertions.assertEquals(expectedSize, productQuantities.size());
	}
	
	@Test
	public void testUpdateCatalogAndOffersWithPacksFoundInCart () {
		teller.packedCart.handlePacks(cart, teller.packs, catalog);
		teller.updateCatalogAndOffersWithPacksFoundInCart();
		
        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, teller.offers.size());
	}

}
