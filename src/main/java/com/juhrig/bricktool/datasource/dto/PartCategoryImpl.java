package com.juhrig.bricktool.datasource.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="part_category")
public class PartCategoryImpl implements PartCategory{

    @Id
    @Column(name="category_id")
    protected int partCategoryId;
    @Column(name="category_name", length = 255)
    protected String categoryName;

    @Transient
    protected int hashCode;

    public PartCategoryImpl(){}

    public PartCategoryImpl(int partId, String categoryName){
        this.partCategoryId = partId;
        this.categoryName = categoryName;
        hashCode = -1;
    }

    public int getPartCategoryId() {
        return partCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object obj) {
        return this.partCategoryId == ((PartCategoryImpl)obj).getPartCategoryId()
                && this.categoryName.equals(((PartCategoryImpl)obj).getCategoryName());
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 43;
            int result = 41;
            result = prime * result + partCategoryId;
            result = prime * result + (categoryName == null ? 0 : categoryName.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    public void setPartCategoryId(int partCategoryId) {
        this.partCategoryId = partCategoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
