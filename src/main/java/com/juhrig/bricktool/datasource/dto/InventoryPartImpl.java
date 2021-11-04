package com.juhrig.bricktool.datasource.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;


public class InventoryPartImpl implements InventoryPart{

    long id;
    protected String partNumber;
    protected int inventoryId;
    protected int colorId;
    protected int quantity;
    protected boolean isSpare;
    protected int hashCode;

    public InventoryPartImpl(){}

    public InventoryPartImpl(int inventoryId, String partNumber, int colorId, int quantity, boolean isSpare){
        this.inventoryId = inventoryId;
        this.partNumber = partNumber;
        this.colorId = colorId;
        this.quantity = quantity;
        this.isSpare = isSpare;
        hashCode = -1;
    }

    public InventoryPartImpl(InventoryPart parentPart, int quantity){
        this(parentPart.getInventoryId(), parentPart.getPartNumber(), parentPart.getColorId(), quantity, false);
    }

    @Override
    public int getInventoryId() {
        return inventoryId;
    }

    @Override
    public String getPartNumber() {
        return partNumber;
    }

    @Override
    public int getColorId() {
        return colorId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean isSpare() {
        return isSpare;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    @Override
    public boolean isSamePart(InventoryPart toCompare){
        return (this.inventoryId == toCompare.getInventoryId()
                && this.partNumber.equals(toCompare.getPartNumber())
                && this.colorId == toCompare.getColorId());
    }

    @Override
    public boolean equals(Object obj) {
        return (
                isSamePart((InventoryPartImpl)obj)
                &&this.quantity == ((InventoryPartImpl)obj).getQuantity()
                &&this.isSpare == ((InventoryPartImpl)obj).isSpare()
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 73;
            int result = 101;
            result = prime * result + inventoryId;
            result = prime * result + (isSpare ? 0 : 1);
            result = prime * result + colorId;
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public int compareTo(InventoryPart o) {
        return this.partNumber.compareTo(o.getPartNumber());
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
