package net.notgandhi.invoice.support.tax;

import java.util.Set;

public class TaxCalculator {
    public static Float applyTaxes(Float price, Set<Tax> taxes) {
        Float total = price;

        for (Tax tax : taxes) {
            total+= tax.getBurden(price);
        }

        return total;
    }
}
