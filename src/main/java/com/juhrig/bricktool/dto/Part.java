package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="part")
public class Part {

    @Id
    @Column(name="part_number", length = 32, nullable = false)
    protected String partNumber;

    @Column(name="part_name", length = 255)
    protected String name;
    @Column(name="part_catalog_id")
    protected int partCategoryId;
    @Column(name="part_material", length = 32)
    protected String partMaterial;

    @Transient
    protected int hashCode;


    public Part(){}

    public Part(String partNumber, String name, int partCategoryId, String partMaterial){
        this.partNumber = partNumber;
        this.name = name;
        this.partCategoryId = partCategoryId;
        this.partMaterial = partMaterial;
        hashCode = -1;
    }


    public String getPartNumber() {
        return partNumber;
    }

    public String getName() {
        return name;
    }

    public int getPartCategoryId() {
        return partCategoryId;
    }

    public String getPartMaterial() {
        return partMaterial;
    }

    @Override
    public boolean equals(Object obj) {
        return (
                this.partNumber.equals(((Part)obj).getPartNumber())
                && this.name.equals(((Part)obj).getName())
                && this.partCategoryId == ((Part)obj).getPartCategoryId()
                && this.partMaterial.equals(((Part)obj).getPartMaterial())
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 43;
            int result = 53;
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            result = prime * result + partCategoryId;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (partMaterial == null ? 0 : partMaterial.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public String toString(){
        return String.format("Part Catalog Id: (%d) | Part number: (%s) | name: (%s) | part material: (%s)", partCategoryId, partNumber, name, partMaterial);
    }
}
