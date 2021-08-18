package com.juhrig.bricktool.dto;

import com.juhrig.bricktool.datasource.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity(name="theme")
public class Theme {

    @Id
    final int themeId;
    final String themeName;
    final Integer parentId;

    @Transient
    int hashCode;

    @Transient
    Theme parentTheme;

    @Autowired
    @Transient
    ThemeRepository themeRepository;

    public Theme(int themeId, String themeName, Integer parentId){
        this.themeId = themeId;
        this.themeName = themeName;
        this.parentId = parentId;
        hashCode = -1;
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

    public Theme getParentTheme() {
        if(parentTheme == null){
            parentTheme = themeRepository.getById(parentId);
        }
        return parentTheme;
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
