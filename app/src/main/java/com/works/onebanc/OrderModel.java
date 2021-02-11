package com.works.onebanc;

public class OrderModel {

    private String dishName;
    private int image;
    private String price;

    public OrderModel(String dishName, int image, String price) {
        this.dishName = dishName;
        this.image = image;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
