package com.juhrig.bricktool.dto;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="inventory_part")
public class InventoryPart implements Comparable<InventoryPart>{

    @Id
    final String partNumber;
    final int inventoryId;
    final String colorId;
    int quantity;
    boolean isSpare;

    @Transient
    int hashCode;

    public InventoryPart(int inventoryId, String partNumber, String colorId, int quantity, boolean isSpare){
        this.inventoryId = inventoryId;
        this.partNumber = partNumber;
        this.colorId = colorId;
        this.quantity = quantity;
        this.isSpare = isSpare;
        hashCode = -1;
    }

    public InventoryPart(InventoryPart parentPart, int quantity){
        this(parentPart.getInventoryId(), parentPart.getPartNumber(), parentPart.colorId, quantity, false);
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getColorId() {
        return colorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }


    public boolean isSamePart(InventoryPart toCompare){
        return (this.inventoryId == toCompare.getInventoryId()
                && this.partNumber.equals(toCompare.getPartNumber())
                && this.colorId.equals(toCompare.getColorId()));
    }

    @Override
    public boolean equals(Object obj) {
        return (
                isSamePart((InventoryPart)obj)
                &&this.quantity == ((InventoryPart)obj).getQuantity()
                &&this.isSpare == ((InventoryPart)obj).isSpare()
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 73;
            int result = 101;
            result = prime * result + inventoryId;
            result = prime * result + (isSpare ? 0 : 1);
            result = prime * result + (colorId == null ? 0 : colorId.hashCode());
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public int compareTo(InventoryPart o) {
        return this.partNumber.compareTo(o.getPartNumber());
    }
}
