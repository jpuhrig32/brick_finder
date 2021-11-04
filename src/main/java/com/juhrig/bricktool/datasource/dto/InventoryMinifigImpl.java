package com.juhrig.bricktool.datasource.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

public class InventoryMinifigImpl implements InventoryMinifig {

    long id;
    protected String minifigNumber;
    protected int inventoryId;
    protected int quantity;

    protected int hashCode;

    public InventoryMinifigImpl(){
        id = 0;
    }

    public InventoryMinifigImpl(int inventoryId, String minifigNumber, int quantity){
        this.inventoryId = inventoryId;
        this.minifigNumber = minifigNumber;
        this.quantity = quantity;
        id = 0;
        hashCode = -1;
    }

    public long getId() {
        return id;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public String getMinifigNumber() {
        return minifigNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return (
                this.quantity == ((InventoryMinifigImpl)obj).getQuantity()
                && this.inventoryId == ((InventoryMinifigImpl)obj).getInventoryId()
                && this.minifigNumber.equals(((InventoryMinifigImpl)obj).getMinifigNumber())
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 67;
            int result = 47;
            result = prime * result + quantity;
            result = prime * result + inventoryId;
            result = prime * result + (minifigNumber == null ? 0 : minifigNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMinifigNumber(String minifigNumber) {
        this.minifigNumber = minifigNumber;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
