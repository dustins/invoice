package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.tax.Tax;
import net.notgandhi.invoice.support.tax.TaxCalculator;
import net.notgandhi.invoice.support.tax.TaxStrategy;

import java.util.Set;

/**
 * Dumb implementation of a TaxStrategy where Taxes are applied in whatever order they are iterated in, and
 * with no regard for their applicability.
 */
public class DumbTaxStrategy implements TaxStrategy {
    @Override
    public Float getAdjustment(Float price, Set<Tax> taxes, Set<Taxable> taxables) {
        return this.apply(price, taxes, taxables) - price;
    }

    @Override
    public Float apply(Float price, Set<Tax> taxes, Set<Taxable> taxables) {
        return TaxCalculator.applyTaxes(price, taxes);
    }
}
