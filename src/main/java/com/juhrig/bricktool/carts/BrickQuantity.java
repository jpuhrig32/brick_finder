package com.juhrig.bricktool.carts;

public class BrickQuantity {

    private final Integer id;
    private int quantity;

    public BrickQuantity(int id, int quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }


}
