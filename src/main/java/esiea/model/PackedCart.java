package esiea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackedCart {

	int packElementFound = 0;
	Map<Product, Double> packsFoundAndSumPrice = new HashMap<Product, Double>();
	private List<ProductQuantity> toRemove = new ArrayList<ProductQuantity>();

	public int getPackElementFound() {
		return packElementFound;
	}
	
	public Map<Product, Double> getPacksFoundAndSumPrice() {
		return packsFoundAndSumPrice;
	}

	public List<ProductQuantity> handlePacks (ShoppingCart entireCart, List<Pack> packs, SupermarketCatalog catalog) {
		
    	List<ProductQuantity> productQuantities = entireCart.getItems();
    	for (Pack pack:packs) {
    		Map<Product, Double> packContent = pack.getPackContent();
    		findPackElements(packContent, productQuantities);
    		if (this.packElementFound == packContent.size()) {
    			productQuantities = replacePackElementsByPackAsOneProduct(pack, productQuantities, catalog); 
    		}
    		this.packElementFound = 0;
    	}
    	return productQuantities;
    }
	
	void findPackElements (Map<Product, Double> packContent, List<ProductQuantity> productQuantities) {
		for (Product p:packContent.keySet()){
			for (ProductQuantity pq:productQuantities) {
				if (pq.getProduct()==p && pq.getQuantity()==packContent.get(p)) {
					this.packElementFound++; 
				}
			}
		}
	}
	
	List<ProductQuantity> replacePackElementsByPackAsOneProduct(Pack pack, List<ProductQuantity> productQuantities, SupermarketCatalog catalog) {
		Map<Product, Double> packContent = pack.getPackContent();
		double sumPrice = pack.calcPackOffer(catalog);
		Product packAsProduct = new Product(pack.getPackName(), ProductUnit.Each);
		this.packsFoundAndSumPrice.put(packAsProduct, sumPrice);
		productQuantities.add(new ProductQuantity(packAsProduct, 1.0));
		for (Product p:packContent.keySet()){
			for (ProductQuantity pq:productQuantities) {
				if (pq.getProduct()==p) {
					toRemove.add(pq);
				}
			}
		}
		productQuantities.removeAll(toRemove);
		return productQuantities;
	}

}
