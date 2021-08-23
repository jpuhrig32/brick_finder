package com.juhrig.bricktool.dto;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Component
@Entity(name="inventory_part")
public class InventoryPart implements Comparable<InventoryPart>{

    @Id
    //@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="inventory_part_id_seq")
    @SequenceGenerator(name="inventory_part_id_seq", sequenceName = "inventory_part_id_seq", allocationSize = 50)
    @Column(name="id")
    long id;

    @Column(name="part_number", length = 32)
    protected String partNumber;
    @Column(name="inventory_id")
    protected int inventoryId;
    @Column(name="color_id")
    protected int colorId;
    @Column(name="quantity")
    protected int quantity;
    @Column(name="is_spare_part")
    protected boolean isSpare;

    @Transient
    protected int hashCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_inventory_id", referencedColumnName = "inventory_id")
    InventorySet parentSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_color_id", referencedColumnName = "color_id")
    Color partColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_part_number", referencedColumnName = "part_number")
    Part part;




    public InventoryPart(){}

    public InventoryPart(int inventoryId, String partNumber, int colorId, int quantity, boolean isSpare){
        this.inventoryId = inventoryId;
        this.partNumber = partNumber;
        this.colorId = colorId;
        this.quantity = quantity;
        this.isSpare = isSpare;
        hashCode = -1;
    }

    public InventoryPart(InventoryPart parentPart, int quantity){
        this(parentPart.getInventoryId(), parentPart.getPartNumber(), parentPart.colorId, quantity, false);
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public int getColorId() {
        return colorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }


    public boolean isSamePart(InventoryPart toCompare){
        return (this.inventoryId == toCompare.getInventoryId()
                && this.partNumber.equals(toCompare.getPartNumber())
                && this.colorId == toCompare.getColorId());
    }

    @Override
    public boolean equals(Object obj) {
        return (
                isSamePart((InventoryPart)obj)
                &&this.quantity == ((InventoryPart)obj).getQuantity()
                &&this.isSpare == ((InventoryPart)obj).isSpare()
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 73;
            int result = 101;
            result = prime * result + inventoryId;
            result = prime * result + (isSpare ? 0 : 1);
            result = prime * result + colorId;
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public int compareTo(InventoryPart o) {
        return this.partNumber.compareTo(o.getPartNumber());
    }
}
