package com.juhrig.bricktool.carts;


import com.juhrig.bricktool.dto.Brick;
import com.juhrig.bricktool.dto.Set;

import javax.persistence.Entity;
import java.util.List;

//@Entity
public class UserBrickInventory extends BrickCart {

    String userName;
    String inventoryName;

    public UserBrickInventory(String userName, String inventoryName){
        this.userName = userName;
        this.inventoryName = inventoryName;
    }

    public void addSet(Set brickSet){

    }

    public void addBrick(Brick brick){

    }

    public void addBricks(List<Brick> bricks){

    }


}
