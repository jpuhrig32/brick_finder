package com.juhrig.bricktool.datasource.dto;

import javax.persistence.*;

public class ElementImpl implements Element{

    protected String elementId;
    protected String partNum;
    protected int colorId;
    protected int hashCode;

    public ElementImpl(){}

    public ElementImpl(String elementId, String partNum, int colorId){
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
                this.elementId == ((ElementImpl)obj).getElementId()
                &&this.partNum.equals(((ElementImpl)obj).getPartNum())
                &&this.getColorId() == ((ElementImpl)obj).getColorId()
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

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
