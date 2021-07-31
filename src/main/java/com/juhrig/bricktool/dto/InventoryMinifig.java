package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_minifig")
public class InventoryMinifig {

    @Id
    @SequenceGenerator(name="INVENTORY_MINIFIG_SEQ_GEN", sequenceName = "INVENTORY_MINIFIG_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_MINIFIG_SEQ_GEN")
    private int id;
    final int inventoryId;
    final String minifigNumber;
    final int quantity;
    int hashCode;

    public InventoryMinifig(int inventoryId, String minifigNumber, int quantity){
        this.inventoryId = inventoryId;
        this.minifigNumber = minifigNumber;
        this.quantity = quantity;
        hashCode = -1;
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
}
