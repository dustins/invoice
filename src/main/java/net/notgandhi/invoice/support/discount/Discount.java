package net.notgandhi.invoice.support.discount;

import net.notgandhi.invoice.domain.model.LineItem;

import java.util.Set;

public interface Discount {
    public Float applyDiscount(Float price);

    public Float getDiscount(Float price);

    public boolean applicable(Set<LineItem> lineItems);
}
