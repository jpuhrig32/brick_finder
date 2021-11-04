package com.juhrig.bricktool.carts;

import com.juhrig.bricktool.datasource.dto.InventoryPart;
import com.juhrig.bricktool.datasource.dto.InventoryPartImpl;
import com.juhrig.bricktool.datasource.dto.Minifig;
import com.juhrig.bricktool.datasource.dto.MinifigImpl;

import java.util.ArrayList;
import java.util.List;

public class BrickSet extends BrickCart {

    private final String displayName;
    private final String idNumber;
    private final String idWithVersion;
    private final List<Minifig> minifigs;

    public BrickSet(String displayName, String idWithVersion, List<InventoryPart> bricks, List<Minifig> minifigs){
        super();
        this.displayName = displayName;
        this.idWithVersion = idWithVersion;
        this.idNumber = idWithVersion.split("-")[0];
        if(bricks == null) {
            this.bricks = new ArrayList<>();
        }
        else{
            this.bricks = bricks;
        }
        if(minifigs == null) {
            this.minifigs = new ArrayList<>();
        }
        else{
            this.minifigs = minifigs;
        }
    }



}
