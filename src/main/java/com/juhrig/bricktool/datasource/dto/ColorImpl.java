package com.juhrig.bricktool.datasource.dto;

public class ColorImpl implements Color {
    int colorId;
    String colorName;
    String colorRGB;
    boolean isTrans;
    protected int hashCode;

    public ColorImpl(){}

    public ColorImpl(int colorId, String colorName, String colorRGB, boolean isTrans){
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorRGB = colorRGB;
        this.isTrans = isTrans;
        hashCode = -1;
    }

    @Override
    public int getColorId() {
        return colorId;
    }

    @Override
    public String getColorName() {
        return colorName;
    }

    @Override
    public String getColorRGB() {
        return colorRGB;
    }

    @Override
    public boolean isTrans() {
        return isTrans;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.colorId == ((ColorImpl)obj).getColorId()
                && this.colorName.equals(((ColorImpl)obj).getColorName())
                && this.colorRGB.equals(((ColorImpl)obj).colorRGB)
                && this.isTrans == ((ColorImpl)obj).isTrans()
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
