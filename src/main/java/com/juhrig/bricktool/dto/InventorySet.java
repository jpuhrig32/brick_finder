package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_set")
public class InventorySet {


    @Id
    final int inventoryId;
    final String setNumber;
    final int quantity;
    int revision;
    int hashCode;


    public InventorySet(int inventoryId, String setNumber, int quantity){
        this(inventoryId, setNumber, quantity, 1);
    }

    public InventorySet(int inventoryId, String setNumber, int quantity, int revision){
        this.inventoryId = inventoryId;
        this.setNumber = setNumber;
        this.quantity = quantity;
        this.hashCode = -1;
        this.revision = revision;
    }


    public int getInventoryId() {
        return inventoryId;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRevision() {
        return revision;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.inventoryId == ((InventorySet)obj).getInventoryId()
                && this.setNumber.equals(((InventorySet)obj).getSetNumber())
                && this.quantity == ((InventorySet)obj).getQuantity()
        );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 73;
            int result = 101;
            result = prime * result + inventoryId;
            result = prime * result + quantity;
            result = prime * result + revision;
            result = prime * result + (setNumber == null ? 0 : setNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
