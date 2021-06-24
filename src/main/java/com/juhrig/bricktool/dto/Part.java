package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part")
public class Part {

    @Id
    @SequenceGenerator(name="PART_SEQ_GEN", sequenceName = "PART_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_SEQ_GEN")
    int id;
    int partNumber;
    String name;
    int partCatalogId;
    String partMaterial;

    public Part(int partNumber, String name, int partCatalogId, String partMaterial){
        this.partNumber = partNumber;
        this.name = name;
        this.partCatalogId = partCatalogId;
        this.partMaterial = partMaterial;
    }

}
