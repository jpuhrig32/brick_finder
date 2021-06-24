package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part_relationship")
public class PartRelationship {

    @Id
    @SequenceGenerator(name="PART_RELATIONSHIP_SEQ_GEN", sequenceName = "PART_RELATIONSHIP_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_RELATIONSHIP_SEQ_GEN")
    int id;
    String relationshipType;
    String childPartNumber;
    String parentPartNumber;

    public PartRelationship(String relationshipType, String childPartNumber, String parentPartNumber){
        this.relationshipType = relationshipType;
        this.childPartNumber = childPartNumber;
        this.parentPartNumber = parentPartNumber;
    }
}
