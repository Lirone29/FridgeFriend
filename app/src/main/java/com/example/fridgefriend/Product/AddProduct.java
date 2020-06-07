package com.example.fridgefriend.Product;

public class AddProduct {

   String action;
   int product_id;


    public AddProduct(int product_id) {
        this.action="ADD";
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
