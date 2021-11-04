package com.juhrig.bricktool.datasource.dto;

public interface InventoryPart extends Comparable<InventoryPart> {

     long getId();

     void setId(long id);

     int getInventoryId();

     String getPartNumber();

     int getColorId();

     int getQuantity();

     boolean isSpare();

     void setQuantity(int quantity);

     void setSpare(boolean spare);

     boolean isSamePart(InventoryPart toCompare);

     int compareTo(InventoryPart o);
}
