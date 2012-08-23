package net.notgandhi.invoice.domain.model;

/**
 * Discount implementation that represents a percent based rate discount.
 */
public class PercentDiscount extends AbstractDiscount {
    private Float discountAmount;

    public PercentDiscount(Float discountAmount) {
        this.setDiscountAmount(discountAmount);
    }

    @Override
    public Float getDiscount(Float price) {
        return (price * this.discountAmount());
    }

    public Float discountAmount() {
        return discountAmount;
    }

    private void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
