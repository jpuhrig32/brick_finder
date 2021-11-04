package com.juhrig.bricktool.datasource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity(name="inventory_set")
public class InventorySetImpl implements InventorySet{


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


    public InventorySetImpl(){}

    public InventorySetImpl(int inventoryId, String setNumber, int quantity){
        this(inventoryId, setNumber, quantity, 1);
    }

    public InventorySetImpl(int inventoryId, String setNumber, int quantity, int revision){
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
        return (this.inventoryId == ((InventorySetImpl)obj).getInventoryId()
                && this.setNumber.equals(((InventorySetImpl)obj).getSetNumber())
                && this.quantity == ((InventorySetImpl)obj).getQuantity()
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

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }


}
