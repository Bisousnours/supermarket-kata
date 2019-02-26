package esiea.model;

import java.util.HashMap;
import java.util.Map;

public class Pack {
    Map<Product, Double> packContent = new HashMap<>();
    String packName;

    public Pack(String packName) {
    	this.packName = packName;
    }
    
    public Map<Product, Double> getPackContent() {
        return this.packContent;
    }
    
    public String getPackName(){
    	return this.packName;
    }

    public void addPackItem(Product p, Double quantity){
        this.packContent.put(p, quantity);
    }

    public double calcPackOffer(SupermarketCatalog catalog){
        double sumPack = 0.0;
        for (Product p:packContent.keySet()){
            sumPack=sumPack+catalog.getUnitPrice(p)*packContent.get(p);
        }
        //Product packAsProduct = new Product(packName, ProductUnit.Each);
        //Offer tenPercent = new TenPercentDiscount(SpecialOfferType.TenPercentDiscount, packAsProduct, 10.0);
        //Discount discount = tenPercent.amountDiscountCalc(1.0, sumPack);
        return sumPack;
    }
}
