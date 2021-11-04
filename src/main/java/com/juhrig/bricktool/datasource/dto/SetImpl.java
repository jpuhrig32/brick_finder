package com.juhrig.bricktool.datasource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.juhrig.bricktool.carts.BrickCart;
import com.juhrig.bricktool.carts.BrickCartComparisonResult;
import com.juhrig.bricktool.datasource.repositories.InventorySetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Component
public class SetImpl extends BrickCart implements Set
{

    protected String setNumber;
    protected String name;
    protected int year;
    protected int themeId;
    protected int numParts;

    protected int hashCode;


    boolean isBrickInventoryDirty;

    public SetImpl(){}

    public SetImpl(String setNumber, String name, int year, int themeId, int numParts){
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


    @Override
    public List<InventoryPart> getBricks() {
        return super.getBricks();
    }


    @Override
    public BrickCartComparisonResult compareCarts(BrickCart toCompare) {
        return super.compareCarts(toCompare);
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
        return this.setNumber.equals(((SetImpl)obj).getSetNumber())
                && this.name.equals(((SetImpl)obj).getName())
                && this.year == ((SetImpl)obj).getYear()
                && this.themeId == ((SetImpl)obj).getThemeId()
                && this.numParts == ((SetImpl)obj).getNumParts();
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

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public void setNumParts(int numParts) {
        this.numParts = numParts;
    }

    public void setBrickInventoryDirty(boolean brickInventoryDirty) {
        isBrickInventoryDirty = brickInventoryDirty;
    }


}
