package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="brick")
public class Brick {


    @Id
    @Column(name="partNum")
    protected String partNum;
    protected String description;
    protected String material;
    protected int catalogId;

    public Brick(String partNum, String description,  int catalogId, String material){
        this.partNum = partNum;
        this.description = description;
        this.material = material;
        this.catalogId = catalogId;
    }

}
