package net.notgandhi.invoice.domain.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.notgandhi.invoice.support.discount.Discount;
import net.notgandhi.invoice.support.discount.DiscountStrategy;
import net.notgandhi.invoice.support.tax.TaxStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Invoice implements Totalable {
    private DiscountStrategy discountStrategy;

    private TaxStrategy taxStrategy;

    private Set<LineItem> lineItems;

    private List<Discount> discounts;

    public Invoice(DiscountStrategy discountStrategy, TaxStrategy taxStrategy) {
        this(discountStrategy, taxStrategy, new HashSet<LineItem>(), new ArrayList<Discount>());
    }

    public Invoice(DiscountStrategy discountStrategy, TaxStrategy taxStrategy, Set<LineItem> lineItems, List<Discount> discounts) {
        this.setDiscountStrategy(discountStrategy);
        this.setTaxStrategy(taxStrategy);
        this.setLineItems(lineItems);
        this.setDiscounts(discounts);
    }

    @Override
    public Float getUnadjustedSubtotal() {
        Float unadjustedSubtotal = 0f;

        for (LineItem item : this.lineItems()) {
            unadjustedSubtotal+= item.getUnadjustedSubtotal();
        }

        return unadjustedSubtotal;
    }

    private Float getLineItemsSubtotal(DiscountStrategy discountStrategy) {
        Float lineItemsSubtotal = 0f;

        for (LineItem item : this.lineItems()) {
            lineItemsSubtotal+= item.getSubtotal(discountStrategy, new DumbTaxStrategy());
        }

        return lineItemsSubtotal;
    }

    @Override
    public Float getSubtotal(DiscountStrategy discountStrategy, TaxStrategy taxStrategy) {
        Float lineItemsSubtotal = this.getLineItemsSubtotal(discountStrategy);
        Float subtotal = this.discountStrategy().apply(lineItemsSubtotal, this.discounts());

        return subtotal;
    }

    public Float getTotal() {
        return this.getSubtotal(this.discountStrategy(), this.taxStrategy());
    }

    private Set<LineItem> lineItems() {
        return this.lineItems;
    }

    public ImmutableSet<LineItem> getLineItems() {
        return new ImmutableSet.Builder<LineItem>().addAll(this.lineItems()).build();
    }

    private void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public boolean addLineItem(LineItem lineItem) {
        return this.lineItems().add(lineItem);
    }

    public boolean removeLineItem(LineItem lineItem) {
        return this.lineItems().remove(lineItem);
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

    public DiscountStrategy discountStrategy() {
        return discountStrategy;
    }

    private void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public TaxStrategy taxStrategy() {
        return taxStrategy;
    }

    private void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }
}
