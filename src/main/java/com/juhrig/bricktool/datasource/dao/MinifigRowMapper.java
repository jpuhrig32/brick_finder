package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Minifig;
import com.juhrig.bricktool.datasource.dto.MinifigImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MinifigRowMapper implements RowMapper<Minifig> {
    @Override
    public Minifig mapRow(ResultSet rs, int rowNum) throws SQLException {
        MinifigImpl result = null;
        if(rs != null) {
            result = new MinifigImpl();
            result.setMinifigId(rs.getString("minifig_id"));
            result.setName(rs.getString("minifig_name"));
            result.setNumParts(rs.getInt("part_count"));
        }
        return result;
    }
}
