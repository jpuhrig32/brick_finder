package com.juhrig.bricktool.carts;

import com.juhrig.bricktool.datasource.dto.SetImpl;

//@Entity
public class UserBrickInventory extends BrickCart {

    String userName;
    String inventoryName;

    public UserBrickInventory(String userName, String inventoryName){
        this.userName = userName;
        this.inventoryName = inventoryName;
    }

    public void addSet(SetImpl brickSet){

    }



}
