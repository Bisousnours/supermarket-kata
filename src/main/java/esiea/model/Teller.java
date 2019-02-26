package esiea.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    Map<Product, Offer> offers = new HashMap<>();
    List<Pack> packs = new ArrayList<>();
    private PackedCart packedCart = new PackedCart();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        if (offerType == SpecialOfferType.TwoForAmount) {
            this.offers.put(product, new TwoForAmount(offerType, product, argument));
        }else if (offerType == SpecialOfferType.TenPercentDiscount){
            this.offers.put(product, new TenPercentDiscount(offerType, product, argument));
        }else if (offerType == SpecialOfferType.ThreeForTwo){
            this.offers.put(product, new ThreeForTwo(offerType, product, argument));
        }else{
            this.offers.put(product, new FiveForAmount(offerType, product, argument));
        }
    }
     public void addNewPack (Pack pack){
        this.packs.add(pack);
     }
     
     void updateCatalogAndOffersWithPacksFoundInCart () {
         Map<Product, Double> packsFoundAndSumPrice = packedCart.getPacksFoundAndSumPrice();
         for (Product p:packsFoundAndSumPrice.keySet()) {
         	this.catalog.addProduct(p, packsFoundAndSumPrice.get(p));
         	addSpecialOffer(SpecialOfferType.TenPercentDiscount, p, 10.0);
         }
     }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = packedCart.handlePacks(theCart, packs, catalog);
        updateCatalogAndOffersWithPacksFoundInCart();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }

}
