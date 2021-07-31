package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part")
public class Part {

    @Id
    @SequenceGenerator(name="PART_SEQ_GEN", sequenceName = "PART_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PART_SEQ_GEN")
    int id;
    final String partNumber;
    final String name;
    final int partCatalogId;
    final String partMaterial;
    int hashCode;

    public Part(String partNumber, String name, int partCatalogId, String partMaterial){
        this.partNumber = partNumber;
        this.name = name;
        this.partCatalogId = partCatalogId;
        this.partMaterial = partMaterial;
        hashCode = -1;
    }

    public int getId() {
        return id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getName() {
        return name;
    }

    public int getPartCatalogId() {
        return partCatalogId;
    }

    public String getPartMaterial() {
        return partMaterial;
    }

    @Override
    public boolean equals(Object obj) {
        return (
                this.partNumber.equals(((Part)obj).getPartNumber())
                && this.name.equals(((Part)obj).getName())
                && this.partCatalogId == ((Part)obj).getPartCatalogId()
                && this.partMaterial.equals(((Part)obj).getPartMaterial())
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 43;
            int result = 53;
            result = prime * result + (partNumber == null ? 0 : partNumber.hashCode());
            result = prime * result + partCatalogId;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (partMaterial == null ? 0 : partMaterial.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public String toString(){
        return String.format("Part Catalog Id: (%d) | Part number: (%s) | name: (%s) | part material: (%s)", partCatalogId, partNumber, name, partMaterial);
    }
}
