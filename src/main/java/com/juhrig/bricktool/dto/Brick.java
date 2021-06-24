package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="brick")
public class Brick {

    @Id
    @SequenceGenerator(name="BRICK_SEQ_GEN", sequenceName = "BRICK_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BRICK_SEQ_GEN")
    protected int id;
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
