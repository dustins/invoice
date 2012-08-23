package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.discount.Discount;

import java.util.Set;

public class ZeroDiscount implements Discount {
    @Override
    public Float applyDiscount(Float price) {
        return price;
    }

    @Override
    public Float getDiscount(Float price) {
        return 0f;
    }

    @Override
    public boolean applicable(Set<LineItem> lineItems) {
        return true;
    }
}
