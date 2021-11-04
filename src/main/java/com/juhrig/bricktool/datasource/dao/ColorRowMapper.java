package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Color;
import com.juhrig.bricktool.datasource.dto.ColorImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorRowMapper implements RowMapper<Color> {
    @Override
    public Color mapRow(ResultSet rs, int rowNum) throws SQLException {
        Color color = null;
        if(rs != null){
            color = new ColorImpl(
                    rs.getInt("color_id"),
                    rs.getString("color_name"),
                    rs.getString("color_rgb"),
                    rs.getBoolean("is_transparent")
            );
        }
        return color;
    }
}
