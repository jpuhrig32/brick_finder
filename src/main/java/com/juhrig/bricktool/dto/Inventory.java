package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory")
public class Inventory {
    @Id
    @SequenceGenerator(name="INVENTORY_SEQ_GEN", sequenceName = "INVENTORY_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_SEQ_GEN")
    int id;
    final int inventoryId;
    final int version;
    final String setNumber;
    int hashCode;

    public Inventory(int inventoryId, int version, String setNumber){
        this.inventoryId = inventoryId;
        this.version = version;
        this.setNumber = setNumber;
        hashCode = -1;
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

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 29;
            int result = 71;
            result = prime * result + inventoryId;
            result = prime * result + version;
            result = prime * result + (setNumber == null ? 0 : setNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
