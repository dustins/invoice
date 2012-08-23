package net.notgandhi.invoice.domain.model;

/**
 * Discount implementation that represents a flat rate discount.
 */
public class FlatDiscount extends AbstractDiscount {
    private Float discountAmount;

    public FlatDiscount(Float discountAmount) {
        this.setDiscountAmount(discountAmount);
    }

    @Override
    public Float getDiscount(Float price) {
        return this.discountAmount();
    }

    public Float discountAmount() {
        return discountAmount;
    }

    private void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
