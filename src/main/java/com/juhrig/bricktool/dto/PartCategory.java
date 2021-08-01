package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="part_category")
public class PartCategory {

    @Id
    final int partId;
    final String categoryName;

    @Transient
    int hashCode;

    public PartCategory(int partId, String categoryName){
        this.partId = partId;
        this.categoryName = categoryName;
        hashCode = -1;
    }

    public int getPartId() {
        return partId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object obj) {
        return this.partId == ((PartCategory)obj).getPartId()
                && this.categoryName.equals(((PartCategory)obj).getCategoryName());
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 43;
            int result = 41;
            result = prime * result + partId;
            result = prime * result + (categoryName == null ? 0 : categoryName.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
