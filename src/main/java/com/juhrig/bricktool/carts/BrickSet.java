package com.juhrig.bricktool.carts;

public class BrickSet extends BrickCart{

    private final String displayName;
    private final String idNumber;
    private final String idWithVersion;


    public BrickSet(String displayName, String idWithVersion){
        super();
        this.displayName = displayName;
        this.idWithVersion = idWithVersion;
        this.idNumber = idWithVersion.split("-")[0];
    }



}
