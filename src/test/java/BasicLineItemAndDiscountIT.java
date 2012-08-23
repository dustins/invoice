import net.notgandhi.invoice.domain.model.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BasicLineItemAndDiscountIT {
    @Test
    public void testSingleLineItemAndSingleDiscount() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));

        invoice.addDiscount(new FlatDiscount(1f));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(4f, invoice.getTotal());
    }

    @Test
    public void testSingleLineItemAndMultipleDiscount() {
        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));

        invoice.addDiscount(new FlatDiscount(1f));
        invoice.addDiscount(new PercentDiscount(0.25f));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(3f, invoice.getTotal());
    }

    @Test
    public void testMultipleLineItemAndSingleDiscount() {
        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));
        invoice.addLineItem(new LineItem(new Fee(3f)));

        invoice.addDiscount(new FlatDiscount(2f));
        assertEquals(8f, invoice.getUnadjustedSubtotal());
        assertEquals(6f, invoice.getTotal());
    }

    @Test
    public void testMultipleLineItemAndMultipleDiscount() {
        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));
        invoice.addLineItem(new LineItem(new Fee(3f)));

        invoice.addDiscount(new FlatDiscount(2f));
        invoice.addDiscount(new PercentDiscount(1/3f));
        assertEquals(8f, invoice.getUnadjustedSubtotal());
        assertEquals(4f, invoice.getTotal());
    }
}
