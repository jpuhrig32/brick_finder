package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.PartRelationship;
import com.juhrig.bricktool.datasource.dto.PartRelationshipImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartRelationshipRowMapper implements RowMapper<PartRelationship> {
    @Override
    public PartRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartRelationshipImpl result = null;
        if(rs != null){
            result = new PartRelationshipImpl();
            result.setId(rs.getLong("id"));
            result.setRelationshipType(rs.getString("relationship_type"));
            result.setChildPartNumber(rs.getString("child_part_number"));
            result.setParentPartNumber(rs.getString("parent_part_number"));
        }
        return result;
    }
}
