package org.example.entity;

import jakarta.persistence.*;

@Entity
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId ;

    private String productName ;
    private  int price ;



    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }

    public String getproductName(){
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public int getprice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
