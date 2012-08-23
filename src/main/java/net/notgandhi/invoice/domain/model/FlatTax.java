package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.tax.Tax;

/**
 * Tax implementation that represents a flat rate tax.
 */
public class FlatTax implements Tax {
    private String name;

    private Float taxAmount;

    public FlatTax(String name, Float taxAmount) {
        this.setName(name);
        this.setTaxAmount(taxAmount);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Float getBurden(Float price) {
        return this.taxAmount();
    }

    private void setName(String name) {
        this.name = name;
    }

    public Float taxAmount() {
        return taxAmount;
    }

    private void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }
}
