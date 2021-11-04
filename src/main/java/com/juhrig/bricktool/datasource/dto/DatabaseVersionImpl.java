package com.juhrig.bricktool.datasource.dto;

import java.sql.Date;

public class DatabaseVersionImpl implements DatabaseVersion{

    int id;
    int versionNumber;
    Date versionDate;

    public DatabaseVersionImpl(){
        id = 0;
        versionNumber = 0;
        versionDate = new Date(0);
    }

    public DatabaseVersionImpl(int id, int versionNumber, Date versionDate){
        this.id = id;
        this.versionNumber = versionNumber;
        this.versionDate = versionDate;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public Date getVersionDate() {
        return versionDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void setVersionDate(Date versionDate) {
        this.versionDate = versionDate;
    }
}
