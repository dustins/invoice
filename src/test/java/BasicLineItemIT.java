import net.notgandhi.invoice.domain.model.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BasicLineItemIT {
    @Test
    public void testSingleLineItem() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));
        assertEquals(5f, invoice.getUnadjustedSubtotal());
        assertEquals(5f, invoice.getTotal());
    }

    @Test
    public void testMultipleLineItem() {
        Invoice invoice = new Invoice(new DumbDiscountStrategy(), new DumbTaxStrategy());

        invoice.addLineItem(new LineItem(new Fee(5f)));
        invoice.addLineItem(new LineItem(new Fee(3f)));
        assertEquals(8f, invoice.getUnadjustedSubtotal());
        assertEquals(8f, invoice.getTotal());
    }
}
