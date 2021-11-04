package com.juhrig.bricktool.datasource.dto;

public interface InventorySet {

    int getInventoryId();

    String getSetNumber();

    int getQuantity();

    int getRevision();

    void setInventoryId(int inventoryId);

    void setSetNumber(String setNumber);

    void setQuantity(int quantity);

    void setRevision(int revision);

    void setHashCode(int hashCode);
}
