package net.notgandhi.invoice.support.tax;

import net.notgandhi.invoice.domain.model.Taxable;

import java.util.Set;

public interface TaxStrategy {
    public Float getAdjustment(Float price, Set<Tax> taxes, Set<Taxable> taxables);

    public Float apply(Float price, Set<Tax> taxes, Set<Taxable> taxables);
}
