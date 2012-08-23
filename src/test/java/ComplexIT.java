import net.notgandhi.invoice.domain.model.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ComplexIT {
    @Test
    public void testSingleLineItemWithSalesTaxAndFlatDiscount() {
        RuleBasedTaxStrategy taxStrategy = new RuleBasedTaxStrategy();
        taxStrategy.addRule(new SalesTaxExemptRule());

        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), taxStrategy);

        LineItem lineItem = new LineItem(new Fee(10f));
        lineItem.addTax(new SalesTax(0.05f));
        lineItem.addDiscount(new FlatDiscount(2f));
        invoice.addLineItem(lineItem);

        assertEquals(10f, invoice.getUnadjustedSubtotal());
        assertEquals(8.4f, invoice.getTotal());
    }
}
