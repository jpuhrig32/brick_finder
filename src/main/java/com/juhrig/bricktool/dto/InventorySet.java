package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity(name="inventory_set")
public class InventorySet {


    @Id
    @Column(name="inventory_id", nullable = false)
    protected int inventoryId;

    @Column(name="set_number", length=32)
    protected String setNumber;

    @Column(name="set_quantity")
    protected int quantity;

    @Column(name="revision_number")
    protected int revision;

    @Transient
    protected int hashCode;

    @ManyToOne
    @JoinColumn(name="parent_set_number", referencedColumnName = "set_number")
    Set parentSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="parentSet")
    List<InventoryMinifig> minifigs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentSet")
    List<InventoryPart> parts;

    public InventorySet(){}

    public InventorySet(int inventoryId, String setNumber, int quantity){
        this(inventoryId, setNumber, quantity, 1);
    }

    public InventorySet(int inventoryId, String setNumber, int quantity, int revision){
        this.inventoryId = inventoryId;
        this.setNumber = setNumber;
        this.quantity = quantity;
        this.hashCode = -1;
        this.revision = revision;
    }


    public int getInventoryId() {
        return inventoryId;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRevision() {
        return revision;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.inventoryId == ((InventorySet)obj).getInventoryId()
                && this.setNumber.equals(((InventorySet)obj).getSetNumber())
                && this.quantity == ((InventorySet)obj).getQuantity()
        );
    }

    @Override
    public int hashCode() {
        final int prime = 73;
        int result = 101;
        result = prime * result + inventoryId;
        result = prime * result + quantity;
        result = prime * result + revision;
        result = prime * result + (setNumber == null ? 0 : setNumber.hashCode());
        hashCode = result;
        return hashCode;
    }
}
