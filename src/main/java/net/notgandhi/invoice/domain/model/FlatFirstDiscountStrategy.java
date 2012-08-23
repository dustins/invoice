package net.notgandhi.invoice.domain.model;

import net.notgandhi.invoice.support.discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class FlatFirstDiscountStrategy extends DumbDiscountStrategy {
    @Override
    public Float getAdjustment(Float price, List<Discount> discounts) {
        final List<Discount> flatDiscountList = new ArrayList<Discount>();
        final List<Discount> nonFlatDiscountList = new ArrayList<Discount>();

        for (Discount discount : discounts) {
            if (FlatTax.class.isInstance(discount)) {
                flatDiscountList.add(discount);
                continue;
            }

            nonFlatDiscountList.add(discount);
        }

        List<Discount> orderedDiscounts =  new ArrayList<Discount>() {{
            addAll(flatDiscountList);
            addAll(nonFlatDiscountList);
        }};

        return super.getAdjustment(price, orderedDiscounts);
    }

//    @Override
//    public Float apply(Float price, List<Discount> discounts) {
//        return (price - this.getAdjustment(price, discounts));
//    }
}
