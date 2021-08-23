package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="part_relationship")
public class PartRelationship {

    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_relationship_id_seq")
    @SequenceGenerator(name="part_relationship_id_seq", sequenceName = "part_relationship_id_seq", allocationSize = 1)
    @Column(name="id")
    long id;

    @Column(name="relationship_type", length = 64)
    protected String relationshipType;
    @Column(name="child_part_number", length = 32)
    protected String childPartNumber;
    @Column(name="parent_part_number", length=32)
    protected String parentPartNumber;

    @Transient
    protected int hashCode;

    @ManyToOne
    @JoinColumn(name="ref_child_part_number", referencedColumnName = "part_number")
    Part childPart;

    @ManyToOne
    @JoinColumn(name="ref_parent_part_number", referencedColumnName = "part_number")
    Part parentPart;

    public PartRelationship(){}

    public PartRelationship(String relationshipType, String childPartNumber, String parentPartNumber){
        this.relationshipType = relationshipType;
        this.childPartNumber = childPartNumber;
        this.parentPartNumber = parentPartNumber;
        hashCode = -1;
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
