package esiea.model;

public class FiveForAmount extends Offer {
    public FiveForAmount(SpecialOfferType offerType, Product product, double argument) {
        super(SpecialOfferType.FiveForAmount, product, argument);
    }

    @Override
    public Discount amountDiscountCalc(double quantity, double unitPrice){
        int x =5;
        Discount discount = null;
        int quantityAsInt = (int) quantity;
        int numberOfXs = quantityAsInt / x;

        if (quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (this.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
            discount = new Discount(this.getProduct(), x + " for " + this.argument, discountTotal);
        }

        return discount;
    }
}
