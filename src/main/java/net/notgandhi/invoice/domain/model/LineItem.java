package net.notgandhi.invoice.domain.model;

import com.google.common.collect.ImmutableSet;
import net.notgandhi.invoice.support.discount.Discount;
import net.notgandhi.invoice.support.discount.DiscountStrategy;
import net.notgandhi.invoice.support.tax.Tax;
import net.notgandhi.invoice.support.tax.TaxStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LineItem implements Totalable, Taxable {
    private Fee fee;

    private Set<Tax> taxes;

    private List<Discount> discounts;

    public LineItem(Fee fee, Set<Tax> taxes, List<Discount> discounts) {
        super();
        this.setDiscounts(discounts);
        this.setTaxes(taxes);
        this.setFee(fee);
    }

    public LineItem(Fee fee, Set<Tax> taxes) {
        this(fee, taxes, new ArrayList<Discount>());
    }

    public LineItem(Fee fee) {
        this(fee, new HashSet<Tax>());
    }

    @Override
    public Float getUnadjustedSubtotal() {
        return this.fee().getUnadjustedSubtotal();
    }

    private Float getFeeSubtotal(DiscountStrategy discountStrategy, TaxStrategy taxStrategy) {
        return this.fee().getSubtotal(discountStrategy, taxStrategy);
    }

    @Override
    public Float getSubtotal(DiscountStrategy discountStrategy, TaxStrategy taxStrategy) {
        Float subtotal = discountStrategy.apply(this.getFeeSubtotal(discountStrategy, taxStrategy), this.discounts());

        HashSet<Taxable> taxables = new HashSet<Taxable>();
        taxables.add(this);
        subtotal = taxStrategy.apply(subtotal, this.taxes(), taxables);

        return subtotal;
    }

    public Set<Tax> taxes() {
        return this.taxes;
    }

    public ImmutableSet<Tax> getTaxes() {
        return new ImmutableSet.Builder<Tax>().addAll(this.taxes()).addAll(this.fee().getTaxes()).build();
    }

    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }

    public boolean addTax(Tax tax) {
        return this.taxes().add(tax);
    }

    public boolean removeTax(Tax tax) {
        return this.taxes().remove(tax);
    }

    public Fee fee() {
        return this.fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    private List<Discount> discounts() {
        return this.discounts;
    }

    public ImmutableSet<Discount> getDiscounts() {
        return new ImmutableSet.Builder<Discount>().addAll(this.discounts()).build();
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public boolean addDiscount(Discount discount) {
        return this.discounts().add(discount);
    }

    public boolean removeDiscount(Discount discount) {
        return this.discounts().remove(discount);
    }
}
