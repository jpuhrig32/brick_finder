package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="set")
public class Set 
{
    @Id
    @SequenceGenerator(name="SET_SEQ_GEN", sequenceName = "SET_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SET_SEQ_GEN")
    int id;
    final String setNumber;
    final String name;
    final int year;
    final int themeId;
    final int numParts;
    int hashCode;

    public Set(String setNumber, String name, int year, int themeId, int numParts){
        this.setNumber = setNumber;
        this.name = name;
        this.year = year;
        this.themeId = themeId;
        this.numParts = numParts;
        hashCode = -1;
    }

    public int getId() {
        return id;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getThemeId() {
        return themeId;
    }

    public int getNumParts() {
        return numParts;
    }

    @Override
    public boolean equals(Object obj) {
        return this.setNumber.equals(((Set)obj).getSetNumber())
                && this.name.equals(((Set)obj).getName())
                && this.year == ((Set)obj).getYear()
                && this.themeId == ((Set)obj).getThemeId()
                && this.numParts == ((Set)obj).getNumParts();
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 7;
            int result = 103;
            result = prime * result + year;
            result = prime * result + themeId;
            result = prime * result + numParts;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (setNumber == null ? 0 : setNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
