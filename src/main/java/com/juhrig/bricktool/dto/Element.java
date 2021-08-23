package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name="element")
@Table(name="element")
public class Element {

    @Id
    @Column(name="element_id", length = 32, nullable = false, unique = true)
    protected String elementId;
    @Column(name="part_num", length = 32)
    protected String partNum;
    @Column(name="color_id")
    protected int colorId;

    @Transient
    protected int hashCode;

    @ManyToOne
    @JoinColumn(name="color_id_ref", referencedColumnName = "color_id")
    Color elementColor;

    public Element(){}

    public Element(String elementId, String partNum, int colorId){
        this.elementId = elementId;
        this.partNum = partNum;
        this.colorId = colorId;
        hashCode = -1;
    }

    public String getElementId() {
        return elementId;
    }

    public String getPartNum() {
        return partNum;
    }

    public int getColorId() {
        return colorId;
    }

    @Override
    public boolean equals(Object obj) {
        return  (
                this.elementId == ((Element)obj).getElementId()
                &&this.partNum.equals(((Element)obj).getPartNum())
                &&this.getColorId() == ((Element)obj).getColorId()
        );

    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 13;
            int result = 113;
            result = prime * result + colorId;
            result = prime * result + (elementId == null ? 0 : elementId.hashCode());
            result = prime * result + (partNum == null ? 0 : partNum.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
