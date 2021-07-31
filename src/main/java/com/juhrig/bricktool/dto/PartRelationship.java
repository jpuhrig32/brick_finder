package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part_relationship")
public class PartRelationship {

    @Id
    @SequenceGenerator(name="PART_RELATIONSHIP_SEQ_GEN", sequenceName = "PART_RELATIONSHIP_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_RELATIONSHIP_SEQ_GEN")
    int id;
    final String relationshipType;
    final String childPartNumber;
    final String parentPartNumber;
    int hashCode;

    public PartRelationship(String relationshipType, String childPartNumber, String parentPartNumber){
        this.relationshipType = relationshipType;
        this.childPartNumber = childPartNumber;
        this.parentPartNumber = parentPartNumber;
        hashCode = -1;
    }

    public int getId() {
        return id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getChildPartNumber() {
        return childPartNumber;
    }

    public String getParentPartNumber() {
        return parentPartNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return this.relationshipType.equals(((PartRelationship)obj).getRelationshipType())
                && this.childPartNumber.equals(((PartRelationship)obj).getChildPartNumber())
                && this.parentPartNumber.equals(((PartRelationship)obj).getParentPartNumber());
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 89;
            int result = 3;
            result = prime * result + (relationshipType == null ? 0 : relationshipType.hashCode());
            result = prime * result + (childPartNumber == null ? 0 : childPartNumber.hashCode());
            result = prime * result + (parentPartNumber == null ? 0 : parentPartNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
