package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_minifig")
public class InventoryMinifig {

    @Id
    @SequenceGenerator(name="INVENTORY_MINIFIG_SEQ_GEN", sequenceName = "INVENTORY_MINIFIG_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_MINIFIG_SEQ_GEN")
    private int id;
    int inventoryId;
    String minifigNumber;
    int quantity;

    public InventoryMinifig(int inventoryId, String minifigNumber, int quantity){
        this.inventoryId = inventoryId;
        this.minifigNumber = minifigNumber;
        this.quantity = quantity;
    }

    public int getId() {
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
                this.quantity == ((InventoryMinifig)obj).getQuantity()
                && this.inventoryId == ((InventoryMinifig)obj).getInventoryId()
                && this.minifigNumber.equals(((InventoryMinifig)obj).getMinifigNumber())
                );
    }
}
