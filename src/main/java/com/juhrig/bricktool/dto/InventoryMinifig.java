package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="inventory_minifig")
public class InventoryMinifig {

    @Id
    @SequenceGenerator(name="inventory_minifig_id_seq_gen", sequenceName = "inventory_minifig_id_seq", allocationSize = 50,initialValue = 1)
    // @GeneratedValue(strategy =GenerationType.IDENTITY, generator = "inventory_minifig_id_seq_gen")
    @Column(name="id", nullable = true)
    Long id;

    @Column(name="minifig_number", length = 32, nullable = false)
    protected String minifigNumber;
    @Column(name="set_inventory_id")
    protected int inventoryId;

    @Column(name="minifig_quantity")
    protected int quantity;

    @Transient
    protected int hashCode;

    @ManyToOne
    @JoinColumn(name="minifig_id")
    Minifig parentMinifig;

    @ManyToOne
    @JoinColumn(name="parent_inventory_id", referencedColumnName = "inventory_id")
    InventorySet parentSet;

    public InventoryMinifig(){}

    public InventoryMinifig(int inventoryId, String minifigNumber, int quantity){
        this.inventoryId = inventoryId;
        this.minifigNumber = minifigNumber;
        this.quantity = quantity;
        hashCode = -1;
    }

    public Long getId() {
        return id;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public String getMinifigNumber() {
        return minifigNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return (
                this.quantity == ((InventoryMinifig)obj).getQuantity()
                && this.inventoryId == ((InventoryMinifig)obj).getInventoryId()
                && this.minifigNumber.equals(((InventoryMinifig)obj).getMinifigNumber())
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 67;
            int result = 47;
            result = prime * result + quantity;
            result = prime * result + inventoryId;
            result = prime * result + (minifigNumber == null ? 0 : minifigNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
