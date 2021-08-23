package com.juhrig.bricktool.dto;

import com.juhrig.bricktool.carts.BrickCart;
import com.juhrig.bricktool.carts.BrickCartComparisonResult;
import com.juhrig.bricktool.carts.BrickSet;
import com.juhrig.bricktool.datasource.repositories.InventoryPartRepository;
import com.juhrig.bricktool.datasource.repositories.InventoryRepository;
import com.juhrig.bricktool.datasource.repositories.InventorySetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Entity(name="part_set")
public class Set extends BrickCart
{
    @Id
    @Column(name = "set_number", length = 32)
    protected String setNumber;
    @Column(name="set_name", length = 128)
    protected String name;
    @Column(name="release_year")
    protected int year;
    @Column(name="set_theme_id")
    protected int themeId;
    @Column(name="part_count")
    protected int numParts;

    @Transient
    protected int hashCode;

    @Transient
    List<InventorySet> setInventories;

    @Transient
    boolean isBrickInventoryDirty;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "parentSet")
    List<InventorySet> setInventoryList;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="set_theme", referencedColumnName = "theme_id")
    Theme setTheme;

    public Set(){}

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



    @Override
    public List<InventoryPart> getBricks() {
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
