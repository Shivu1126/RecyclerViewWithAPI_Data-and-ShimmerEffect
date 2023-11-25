package com.androidwithshiv.mobiles.model;

public class MobileDetails {
    private String name;
    private String price;
    private String rating;
    private String description;
    private String pic;

    public MobileDetails(String name, String price, String rating, String description, String pic) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
