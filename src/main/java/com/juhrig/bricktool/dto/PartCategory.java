package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part_category")
public class PartCategory {

    @Id
    @SequenceGenerator(name="PART_CATEGORY_SEQ_GEN", sequenceName = "PART_CATEGORY_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_CATEGORY_SEQ_GEN")
    int id;
    int partId;
    String categoryName;

    public PartCategory(int partId, String categoryName){
        this.partId = partId;
        this.categoryName = categoryName;
    }
}
