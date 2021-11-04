package com.juhrig.bricktool.datasource.dto;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name="theme")
public class ThemeImpl implements Theme {

    @Id
    @Column(name="theme_id")
    protected int themeId;
    @Column(name="theme_name", length = 128)
    protected String themeName;
    @Column(name="parent_theme_id")
    protected Integer parentId;

    @Transient
    protected int hashCode;

    public ThemeImpl(){}

    public ThemeImpl(int themeId, String themeName, Integer parentId){
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


    @Override
    public boolean equals(Object obj) {
        return this.themeId == ((ThemeImpl)obj).getThemeId()
            && this.themeName.equals(((ThemeImpl)obj).getThemeName())
            && this.parentId == ((ThemeImpl)obj).getParentId();
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

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
