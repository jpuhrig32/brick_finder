package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory")
public class Inventory {
    @Id
    @SequenceGenerator(name="INVENTORY_SEQ_GEN", sequenceName = "INVENTORY_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_SEQ_GEN")
    int id;
    int inventoryId;
    int version;
    String setNumber;

    public Inventory(int inventoryId, int version, String setNumber){
        this.inventoryId = inventoryId;
        this.version = version;
        this.setNumber = setNumber;
    }

    public int getId() {
        return id;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getVersion() {
        return version;
    }

    public String getSetNumber() {
        return setNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return (
                this.inventoryId == ((Inventory)obj).getInventoryId()
                && this.version == ((Inventory)obj).getVersion()
                && this.setNumber.equals(((Inventory)obj).getSetNumber())
                );
    }
}
