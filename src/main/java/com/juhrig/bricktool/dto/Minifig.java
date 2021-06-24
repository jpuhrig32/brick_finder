package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="minifig")
public class Minifig {
    @Id
    @SequenceGenerator(name="MINIFIG_SEQ_GEN", sequenceName = "MINIFIG_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MINIFIG_SEQ_GEN")
    int id;
    String minifigId;
    String name;
    int numParts;

    public Minifig(String minifigId, String name, int numParts){
        this.minifigId = minifigId;
        this.name = name;
        this.numParts = numParts;
    }
}
