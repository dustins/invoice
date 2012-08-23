package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.tax.Rule;
import net.notgandhi.invoice.support.tax.Tax;

import java.util.Set;

/**
 * Rule implementation that stops the application of sales tax for IRS exempt customers
 */
public class SalesTaxExemptRule implements Rule {
    @Override
    public boolean applies(Tax tax) {
        return SalesTax.class.isInstance(tax);
    }

    @Override
    public boolean isValid(Set<Taxable> taxables) {
        return false;
    }
}
