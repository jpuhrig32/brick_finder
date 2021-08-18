package com.juhrig.bricktool.dto;

import com.juhrig.bricktool.carts.BrickCart;
import com.juhrig.bricktool.carts.BrickCartComparisonResult;
import com.juhrig.bricktool.carts.BrickSet;
import com.juhrig.bricktool.datasource.repositories.InventoryPartRepository;
import com.juhrig.bricktool.datasource.repositories.InventoryRepository;
import com.juhrig.bricktool.datasource.repositories.InventorySetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name="set")
public class Set extends BrickCart
{
    @Id
    final String setNumber;
    final String name;
    final int year;
    final int themeId;
    final int numParts;

    @Transient
    int hashCode;

    @Transient
    List<InventorySet> setInventories;



    @Transient
    boolean isBrickInventoryDirty;

    @Autowired
    @Transient
    InventorySetRepository inventorySetRepository;

    @Autowired
    @Transient
    InventoryPartRepository inventoryPartRepository;



    public Set(String setNumber, String name, int year, int themeId, int numParts){
        super(null);
        this.setNumber = setNumber;
        this.name = name;
        this.year = year;
        this.themeId = themeId;
        this.numParts = numParts;
        hashCode = -1;
        this.isBrickInventoryDirty = false;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getThemeId() {
        return themeId;
    }

    public int getNumParts() {
        return numParts;
    }

    public List<InventorySet> getSetInventorySets() {
        if(setInventories == null){
            setInventories = inventorySetRepository.getInventorySetsBySet(setNumber);
            Map<Integer, InventorySet> inventoriesById = new LinkedHashMap();
            for(InventorySet is : setInventories){
                if(inventoriesById.containsKey(is.getInventoryId())){
                    InventorySet old = inventoriesById.get(is.getInventoryId());
                    if(old.getRevision() < is.getRevision()){
                        inventoriesById.put(is.getInventoryId(), is);
                    }
                }
            }
            setInventories = inventoriesById.entrySet().stream()
                    .map(ibi -> ibi.getValue())
                    .collect(Collectors.toList());
        }
        return setInventories;
    }

    @Override
    public List<InventoryPart> getBricks() {
        if(this.bricks == null){
            getSetInventorySets();
            List<Integer> inventoryIds = setInventories.stream()
                    .map(si -> si.getInventoryId())
                    .collect(Collectors.toList());
            this.bricks = inventoryPartRepository.getAllPartsByInventoryIds(inventoryIds);
        }
        return super.getBricks();
    }

    @Override
    public BrickCartComparisonResult compareCarts(BrickCart toCompare) {
        return super.compareCarts(toCompare);
    }

    @Override
    public void addBrick(InventoryPart toAdd) {
        if(bricks == null){
            getBricks();
        }
        super.addBrick(toAdd);
        isBrickInventoryDirty = true;
    }

    @Override
    public void addCart(BrickCart toAdd) {
        if(bricks == null){
            getBricks();
        }
        super.addCart(toAdd);
        isBrickInventoryDirty = true;
    }

    public void saveSet(){
        if(isBrickInventoryDirty){
            //TODO: Put in save logic here
        }
        isBrickInventoryDirty = false;
    }

    @Override
    public boolean equals(Object obj) {
        return this.setNumber.equals(((Set)obj).getSetNumber())
                && this.name.equals(((Set)obj).getName())
                && this.year == ((Set)obj).getYear()
                && this.themeId == ((Set)obj).getThemeId()
                && this.numParts == ((Set)obj).getNumParts();
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 7;
            int result = 103;
            result = prime * result + year;
            result = prime * result + themeId;
            result = prime * result + numParts;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (setNumber == null ? 0 : setNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
