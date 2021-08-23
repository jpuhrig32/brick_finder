package com.juhrig.bricktool.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name="color_entity")
public class Color{

    @Id
    @Column(name = "color_id", nullable = false, unique = true)
    int colorId;
    @Column(name="color_name", length = 32)
    String colorName;
    @Column(name="color_rgb", length = 32)
    String colorRGB;
    @Column(name="is_transparent")
    boolean isTrans;

    @Transient
    protected int hashCode;

    public Color(){}
    public Color(int colorId, String colorName, String colorRGB, boolean isTrans){
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorRGB = colorRGB;
        this.isTrans = isTrans;
        hashCode = -1;
    }

    public int getColorId() {
        return colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorRGB() {
        return colorRGB;
    }

    public boolean isTrans() {
        return isTrans;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.colorId == ((Color)obj).getColorId()
                && this.colorName.equals(((Color)obj).getColorName())
                && this.colorRGB.equals(((Color)obj).colorRGB)
                && this.isTrans == ((Color)obj).isTrans()
                );
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 31;
            int result = 89;
            result = prime * result + colorId;
            result = prime * result + (isTrans ? 0 : 1);
            result = prime * result + (colorName == null ? 0 : colorName.hashCode());
            result = prime * result + (colorRGB == null ? 0 : colorRGB.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

}
