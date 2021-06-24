package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="set")
public class Set 
{
    @Id
    @SequenceGenerator(name="SET_SEQ_GEN", sequenceName = "SET_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SET_SEQ_GEN")
    int id;
    String setNumber;
    String name;
    int year;
    int themeId;
    int numParts;

    public Set(String setNumber, String name, int year, int themeId, int numParts){
        this.setNumber = setNumber;
        this.name = name;
        this.year = year;
        this.themeId = themeId;
        this.numParts = numParts;
    }
}
