package net.notgandhi.invoice.domain.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.notgandhi.invoice.support.discount.Discount;
import net.notgandhi.invoice.support.discount.DiscountStrategy;
import net.notgandhi.invoice.support.tax.Tax;
import net.notgandhi.invoice.support.tax.TaxStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fee implements Totalable, Taxable {
    private Float amount;

    private Set<Tax> taxes;

    private List<Discount> discounts;

    public Fee(Float amount, Set<Tax> taxes, List<Discount> discounts) {
        this.setAmount(amount);
        this.setTaxes(taxes);
        this.setDiscounts(discounts);
    }

    public Fee(Float amount) {
        this(amount, new HashSet<Tax>(), new ArrayList<Discount>());
    }

    @Override
    public Float getUnadjustedSubtotal() {
        return this.amount();
    }

    @Override
    public Float getSubtotal(DiscountStrategy discountStrategy, TaxStrategy taxStrategy) {
        Float subtotal = discountStrategy.apply(this.getUnadjustedSubtotal(), this.discounts());

        HashSet<Taxable> taxables = new HashSet<Taxable>();
        taxables.add(this);
        subtotal = taxStrategy.apply(subtotal, this.taxes(), taxables);

        return subtotal;
    }

    public Float amount() {
        return amount;
    }

    private void setAmount(Float amount) {
        this.amount = amount;
    }

    private Set<Tax> taxes() {
        return this.taxes;
    }

    public ImmutableSet<? extends Tax> getTaxes() {
        return new ImmutableSet.Builder<Tax>().addAll(this.taxes()).build();
    }

    private void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }

    public boolean addTax(Tax tax) {
        return this.taxes().add(tax);
    }

    public boolean removeTax(Tax tax) {
        return this.taxes().remove(tax);
    }

    private List<Discount> discounts() {
        return this.discounts;
    }

    public ImmutableList<Discount> getDiscounts() {
        return new ImmutableList.Builder<Discount>().addAll(this.discounts()).build();
    }

    private void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public boolean addDiscount(Discount discount) {
        return this.discounts().add(discount);
    }

    public boolean removeDiscount(Discount discount) {
        return this.discounts().remove(discount);
    }
}
