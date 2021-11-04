package com.juhrig.bricktool.datasource.dto;

public interface PartRelationship {

     Long getId();

     void setId(long id);

     String getRelationshipType();

     String getChildPartNumber();

     String getParentPartNumber();
    
}
