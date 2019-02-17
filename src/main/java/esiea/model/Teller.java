package esiea.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();

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
        }else if (offerType == SpecialOfferType.FiveForAmount){
            this.offers.put(product, new FiveForAmount(offerType, product, argument));
        }
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
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
