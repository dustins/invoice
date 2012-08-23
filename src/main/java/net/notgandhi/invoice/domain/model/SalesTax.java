package net.notgandhi.invoice.domain.model;

/**
 * Simple implementation of a sales tax.
 */
public class SalesTax extends PercentTax {
    public SalesTax(Float taxAmount) {
        this("sales", taxAmount);
    }

    public SalesTax(String name, Float taxAmount) {
        super(name, taxAmount);
    }
}
