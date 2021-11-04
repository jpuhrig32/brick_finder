package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Set;
import com.juhrig.bricktool.datasource.dto.SetImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetRowMapper implements RowMapper<Set> {
    @Override
    public Set mapRow(ResultSet rs, int rowNum) throws SQLException {
        Set result = null;
        if (rs != null) {
            SetImpl returnSet = new SetImpl();
            returnSet.setSetNumber(rs.getString("set_number"));
            returnSet.setName(rs.getString("set_name"));
            returnSet.setYear(rs.getInt("release_year"));
            returnSet.setThemeId(rs.getInt("set_theme_id"));
            returnSet.setNumParts(rs.getInt("part_count"));
            result = returnSet;
        }
        return result;
    }
}
