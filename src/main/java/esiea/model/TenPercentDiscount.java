package esiea.model;

public class TenPercentDiscount extends Offer {

    public TenPercentDiscount(SpecialOfferType offerType, Product product, double argument) {
        super(SpecialOfferType.TenPercentDiscount, product, argument);
    }

    @Override
    public Discount amountDiscountCalc(double quantity, double unitPrice) {
        Discount discount = new Discount(this.getProduct(), this.argument + "% off", quantity * unitPrice * this.argument / 100.0);
        return discount;
    }
}
