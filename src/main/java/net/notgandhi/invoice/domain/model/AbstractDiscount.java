package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.discount.Discount;

import java.util.Set;

/**
 * Abstract implementation of the Discount inteface
 */
public abstract class AbstractDiscount implements Discount {
    @Override
    public Float applyDiscount(Float price) {
        return (price - this.getDiscount(price));
    }

    @Override
    public Float getDiscount(Float price) {
        return 0f;
    }

    @Override
    public boolean applicable(Set<LineItem> lineItems) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
