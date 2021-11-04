package com.juhrig.bricktool.datasource.dto;

import java.sql.Date;

public interface DatabaseVersion {

    int getId();

    int getVersionNumber();

    Date getVersionDate();

}
