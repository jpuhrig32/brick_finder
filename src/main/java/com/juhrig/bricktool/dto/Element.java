package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="element")
public class Element {

    @Id
    @SequenceGenerator(name="ELEMENT_SEQ_GEN", sequenceName = "ELEMENT_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ELEMENT_SEQ_GEN")
    int id;
    final int elementId;
    final String partNum;
    final int colorId;
    int hashCode;


    public Element(int elementId, String partNum, int colorId){
        this.elementId = elementId;
        this.partNum = partNum;
        this.colorId = colorId;
        hashCode = -1;
    }

    public int getId() {
        return id;
    }

    public int getElementId() {
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
            result = prime * result + elementId;
            result = prime * result + (partNum == null ? 0 : partNum.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
