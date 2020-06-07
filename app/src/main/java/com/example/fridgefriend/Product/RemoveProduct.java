package com.example.fridgefriend.Product;

import com.google.gson.annotations.SerializedName;

public class RemoveProduct {


    String action;
    int product_in_fridge_id;


    public RemoveProduct(int product_id) {
        this.action="REMOVE";
        this.product_in_fridge_id = product_id;
    }

    public int getProduct_in_fridge_id() {
        return product_in_fridge_id;
    }

    public void setProduct_in_fridge_id(int product_in_fridge_id) {
        this.product_in_fridge_id = product_in_fridge_id;
    }
}