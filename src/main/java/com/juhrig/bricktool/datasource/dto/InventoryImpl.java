package com.juhrig.bricktool.datasource.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

public class InventoryImpl implements Inventory {
    protected int inventoryId;
    protected int version;
    protected String setNumber;
    protected int hashCode;


    public InventoryImpl(){}

    public InventoryImpl(int inventoryId, int version, String setNumber){
        this.inventoryId = inventoryId;
        this.version = version;
        this.setNumber = setNumber;
        hashCode = -1;
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
                this.inventoryId == ((InventoryImpl)obj).getInventoryId()
                && this.version == ((InventoryImpl)obj).getVersion()
                && this.setNumber.equals(((InventoryImpl)obj).getSetNumber())
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

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

}
