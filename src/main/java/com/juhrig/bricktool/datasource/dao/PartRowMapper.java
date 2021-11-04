package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Part;
import com.juhrig.bricktool.datasource.dto.PartImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRowMapper implements RowMapper<Part> {
    @Override
    public Part mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartImpl result = null;
        if(rs != null){
            result = new PartImpl();
            result.setPartNumber(rs.getString("part_number"));
            result.setName(rs.getString("part_name"));
            result.setPartCategoryId(rs.getInt("part_catalog_id"));
            result.setPartMaterial(rs.getString("part_material"));
        }

        return result;
    }
}
