package com.juhrig.bricktool.dto;

import javax.persistence.*;

@Entity(name="theme")
public class Theme {
    @Id
    @SequenceGenerator(name="THEME_SEQ_GEN", sequenceName = "THEME_SEQ", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="THEME_SEQ_GEN")
    int id;
    final int themeId;
    final String themeName;
    final Integer parentId;
    int hashCode;

    public Theme(int themeId, String themeName, Integer parentId){
        this.themeId = themeId;
        this.themeName = themeName;
        this.parentId = parentId;
        hashCode = -1;
    }

    public int getId() {
        return id;
    }

    public int getThemeId() {
        return themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    @Override
    public boolean equals(Object obj) {
        return this.themeId == ((Theme)obj).getThemeId()
            && this.themeName.equals(((Theme)obj).getThemeName())
            && this.parentId == ((Theme)obj).getParentId();
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            final int prime = 29;
            int result = 23;
            result = prime * result + (parentId == null ? 0 : parentId);
            result = prime * result + themeId;
            result = prime * result + (themeName == null ? 0 : themeName.hashCode());
            hashCode = result;
        }
        return hashCode;
    }
}
