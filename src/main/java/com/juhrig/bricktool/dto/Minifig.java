package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="minifig")
public class Minifig {

    @Id
    final String minifigId;
    final String name;
    final int numParts;

    @Transient
    int hashCode;

    public Minifig(String minifigId, String name, int numParts){
        this.minifigId = minifigId;
        this.name = name;
        this.numParts = numParts;
        hashCode = -1;
    }

    public String getMinifigId() {
        return minifigId;
    }

    public String getName() {
        return name;
    }

    public int getNumParts() {
        return numParts;
    }

    @Override
    public boolean equals(Object obj) {
        return this.minifigId.equals(((Minifig)obj).minifigId)
                && this.name.equals(((Minifig)obj).getName())
                && this.numParts == ((Minifig)obj).getNumParts();
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 17;
            int result = 23;
            result = prime * result + numParts;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (minifigId == null ? 0 : minifigId.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
