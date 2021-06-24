package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="inventory_part")
public class InventoryPart {

    @Id
    @SequenceGenerator(name="INVENTORY_PART_SEQ_GEN", sequenceName = "INVENTORY_PART_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_PART_SEQ_GEN")
    int id;
    int inventoryId;
    String partNumber;
    String colorId;
    int quantity;
    boolean isSpare;

    public InventoryPart(int inventoryId, String partNumber, String colorId, int quantity, boolean isSpare){

    }
}
