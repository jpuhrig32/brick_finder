package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_set")
public class InventorySet {

    @Id
    @SequenceGenerator(name="INVENTORY_SET_SEQ_GEN", sequenceName = "INVENTORY_SET_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_SET_SEQ_GEN")
    private int id;
    int inventoryId;
    String setNumber;
    int quantity;

    public InventorySet(int inventoryId, String minifigNumber, int quantity){
        this.inventoryId = inventoryId;
        this.setNumber = minifigNumber;
        this.quantity = quantity;
    }
}
