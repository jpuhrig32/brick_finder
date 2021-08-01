package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_part")
public class InventoryPart {

    @Id
    final int inventoryId;
    final String partNumber;
    final String colorId;
    final int quantity;
    final boolean isSpare;

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

    @Override
    public boolean equals(Object obj) {
        return (
                this.inventoryId == ((InventoryPart)obj).getInventoryId()
                && this.partNumber.equals(((InventoryPart)obj).getPartNumber())
                && this.colorId.equals(((InventoryPart)obj).getColorId())
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
            result = prime * result + quantity;
            result = prime * result + (isSpare ? 0 : 1);
            result = prime * result + (colorId == null ? 0 : colorId.hashCode());
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
