package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Theme;
import com.juhrig.bricktool.datasource.dto.ThemeImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeRowMapper implements RowMapper<Theme> {
    @Override
    public Theme mapRow(ResultSet rs, int rowNum) throws SQLException {
        ThemeImpl result = null;
        if(rs != null){
            result = new ThemeImpl();
            result.setThemeId(rs.getInt("theme_id"));
            result.setThemeName(rs.getString("theme_name"));
            int parentTheme = rs.getInt("parent_theme_id");
            if(parentTheme == 0){
                result.setParentId(-1);
            }
            else{
                result.setParentId(parentTheme);
            }
        }
        return result;
    }
}
