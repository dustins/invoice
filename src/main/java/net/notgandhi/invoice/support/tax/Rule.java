package net.notgandhi.invoice.support.tax;

import net.notgandhi.invoice.domain.model.Taxable;

import java.util.Set;

public interface Rule {
    public boolean applies(Tax tax);

    public boolean isValid(Set<Taxable> taxables);
}
