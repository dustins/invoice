package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.discount.Discount;
import net.notgandhi.invoice.support.discount.DiscountStrategy;

import java.util.List;

/**
 * Dumb implementation of a DiscountStrategy where Discounts are applied in whatever order they are iterated in.
 */
public class DumbDiscountStrategy implements DiscountStrategy {
    @Override
    public Float getAdjustment(Float price, List<Discount> discounts) {
        Float totalDiscount = 0f;

        for (Discount discount : discounts) {
            totalDiscount+= discount.getDiscount(price-totalDiscount);
        }

        return totalDiscount;
    }

    @Override
    public Float apply(Float price, List<Discount> discounts) {
        return (price - this.getAdjustment(price, discounts));
    }
}
