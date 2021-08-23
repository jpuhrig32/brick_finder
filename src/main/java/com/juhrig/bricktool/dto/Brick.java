package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Component
@Entity(name="brick")
public class Brick {


    @Id
    @Column(name="partNum")
    protected String partNum;
    protected String description;
    protected String material;
    protected int catalogId;

    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = false, updatable = false)
    private Integer id;

    public Brick(){}

    public Brick(String partNum, String description,  int catalogId, String material){
        this.partNum = partNum;
        this.description = description;
        this.material = material;
        this.catalogId = catalogId;
    }

    public String getPartNum() {
        return partNum;
    }

    public String getDescription() {
        return description;
    }

    public String getMaterial() {
        return material;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public Integer getId() {
        return id;
    }
}
