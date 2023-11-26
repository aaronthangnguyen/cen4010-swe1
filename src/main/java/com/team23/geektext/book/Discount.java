package com.team23.geektext.book;

public class Discount {
    private double discountPercent;
    private String publisher;

    public Discount() {}

    public Discount(double discountPercent, String publisher) {
        this.discountPercent = discountPercent;
        this.publisher = publisher;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

