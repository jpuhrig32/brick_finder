package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="color")
public class Color {

    @Id
    @SequenceGenerator(name="INVENTORY_SET_SEQ_GEN", sequenceName = "INVENTORY_SET_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INVENTORY_SET_SEQ_GEN")
    int id;
    int colorId;
    String colorName;
    String colorRGB;
    boolean isTrans;

    public Color(int colorId, String colorName, String colorRGB, boolean isTrans){
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorRGB = colorRGB;
        this.isTrans = isTrans;
    }

    public int getId() {
        return id;
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
}
