import net.notgandhi.invoice.domain.model.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BasicLineItemAndDiscountWithFeeDiscountIT {
    @Test
    public void testSingleLineItemWithFeeDiscount() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        Fee fee = new Fee(5f);
        fee.addDiscount(new FlatDiscount(1f));
        invoice.addLineItem(new LineItem(fee));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(4f, invoice.getTotal());
    }

    @Test
    public void testSingleLineItemAndSingleDiscountWithFeeDiscount() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        Fee fee = new Fee(5f);
        fee.addDiscount(new FlatDiscount(1f));
        invoice.addLineItem(new LineItem(fee));

        invoice.addDiscount(new FlatDiscount(1f));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(3f, invoice.getTotal());
    }

    @Test
    public void testSingleLineItemAndMultipleDiscountWithFeeDiscount() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        Fee fee = new Fee(5f);
        fee.addDiscount(new FlatDiscount(1f));
        invoice.addLineItem(new LineItem(fee));

        invoice.addDiscount(new FlatDiscount(1f));
        invoice.addDiscount(new PercentDiscount(1 / 3f));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(2f, invoice.getTotal());
    }

    @Test
    public void testMultipleLineItemAndSingleDiscountWithFeeDiscount() {
        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), new DumbTaxStrategy());
        Fee fee;

        fee = new Fee(6f);
        fee.addDiscount(new FlatDiscount(2f));
        invoice.addLineItem(new LineItem(fee));

        fee = new Fee(4f);
        fee.addDiscount(new FlatDiscount(2f));
        invoice.addLineItem(new LineItem(fee));

        invoice.addDiscount(new FlatDiscount(2f));
        assertEquals(10f, invoice.getUnadjustedSubtotal());
        assertEquals(4f, invoice.getTotal());
    }

    @Test
    public void testMultipleLineItemAndMultipleDiscountWithFeeDiscount() {
        Invoice invoice = new Invoice(new FlatFirstDiscountStrategy(), new DumbTaxStrategy());
        Fee fee;

        fee = new Fee(6f);
        fee.addDiscount(new FlatDiscount(2f));
        invoice.addLineItem(new LineItem(fee));

        fee = new Fee(4f);
        fee.addDiscount(new FlatDiscount(2f));
        invoice.addLineItem(new LineItem(fee));

        invoice.addDiscount(new FlatDiscount(2f));
        invoice.addDiscount(new PercentDiscount(0.25f));
        assertEquals(10f, invoice.getUnadjustedSubtotal());
        assertEquals(3f, invoice.getTotal());
    }
}
