package net.notgandhi.invoice.support.discount;

import java.util.List;

public interface DiscountStrategy {
    public Float getAdjustment(Float price, List<Discount> discounts);

    public Float apply(Float price, List<Discount> discounts);
}
