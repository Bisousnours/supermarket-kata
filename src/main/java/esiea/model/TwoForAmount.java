package esiea.model;

public class TwoForAmount extends Offer{

    public TwoForAmount(SpecialOfferType offerType, Product product, double argument) {
        super(SpecialOfferType.TwoForAmount, product, argument);
    }

    @Override
    public Discount amountDiscountCalc(double quantity, double unitPrice){
        int x =2;
        Discount discount = null;
        int quantityAsInt = (int) quantity;

        if (quantityAsInt >= 2) {
            double total = this.argument * quantityAsInt / x + quantityAsInt % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            discount = new Discount(this.getProduct(), "2 for " + this.argument, discountN);
        }

        return discount;
    }
}
