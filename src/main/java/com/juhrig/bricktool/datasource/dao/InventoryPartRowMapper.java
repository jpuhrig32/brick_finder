package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventoryPart;
import com.juhrig.bricktool.datasource.dto.InventoryPartImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryPartRowMapper implements RowMapper<InventoryPart> {
    @Override
    public InventoryPart mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventoryPartImpl result = null;
        if(rs != null){
            result = new InventoryPartImpl();
            result.setId(rs.getLong("id"));
            result.setPartNumber(rs.getString("part_number"));
            result.setInventoryId(rs.getInt("inventory_id"));
            result.setColorId(rs.getInt("color_id"));
            result.setQuantity(rs.getInt("quantity"));
            result.setSpare(rs.getBoolean("is_spare_part"));
        }
        return result;
    }
}
