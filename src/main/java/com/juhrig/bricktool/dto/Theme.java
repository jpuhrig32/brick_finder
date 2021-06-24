package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="theme")
public class Theme {
    @Id
    @SequenceGenerator(name="THEME_SEQ_GEN", sequenceName = "THEME_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="THEME_SEQ_GEN")
    int id;
    int themeId;
    String themeName;
    int parentId;

    public Theme(int themeId, String themeName, int parentId){
        this.themeId = themeId;
        this.themeName = themeName;
        this.parentId = parentId;

    }
}
