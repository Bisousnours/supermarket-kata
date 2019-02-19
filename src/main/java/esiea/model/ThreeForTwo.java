package esiea.model;

public class ThreeForTwo extends Offer {

    public ThreeForTwo(SpecialOfferType offerType, Product product, double argument) {
        super(SpecialOfferType.ThreeForTwo, product, argument);
    }
    @Override
    public Discount amountDiscountCalc(double quantity, double unitPrice) {
        Discount discount = null;
        int quantityAsInt = (int) quantity;
        int numberOfXs = quantityAsInt / 3;

        if (quantityAsInt > 2) {
            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(this.getProduct(), "3 for 2", discountAmount);
        }

        return discount;
    }
}
