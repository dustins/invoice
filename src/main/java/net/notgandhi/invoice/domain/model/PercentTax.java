package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.tax.Tax;

/**
 * Tax implementation that represents a percent based rate tax.
 */
public class PercentTax implements Tax {
    private String name;

    private Float taxAmount;

    public PercentTax(String name, Float taxAmount) {
        this.setName(name);
        this.setTaxAmount(taxAmount);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Float getBurden(Float price) {
        return (price * this.taxAmount());
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
