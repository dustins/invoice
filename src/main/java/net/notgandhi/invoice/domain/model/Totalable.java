package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.discount.DiscountStrategy;
import net.notgandhi.invoice.support.tax.TaxStrategy;

public interface Totalable {
    public Float getUnadjustedSubtotal();

    public Float getSubtotal(DiscountStrategy discountStrategy, TaxStrategy taxStrategy);
}
