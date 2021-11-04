package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventorySet;
import com.juhrig.bricktool.datasource.dto.InventorySetImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventorySetRowMapper implements RowMapper<InventorySet> {
    @Override
    public InventorySet mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventorySet result = null;
        if (rs != null) {
            InventorySetImpl inventorySet = new InventorySetImpl();
            inventorySet.setInventoryId(rs.getInt("inventory_id"));
            inventorySet.setSetNumber(rs.getString("set_number"));
            inventorySet.setQuantity(rs.getInt("set_quantity"));
            inventorySet.setRevision(rs.getInt("revision_number"));
            result = inventorySet;
        }
        return result;
    }
}
