package net.notgandhi.invoice.support.tax;

public interface Tax {
    public String name();

    public Float getBurden(Float price);
}
