package com.team23.geektext.book;

public class DiscountDTO {
    private double discountPercent;
    private String publisher;

    public DiscountDTO() {}

    public DiscountDTO(double discountPercent, String publisher) {
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