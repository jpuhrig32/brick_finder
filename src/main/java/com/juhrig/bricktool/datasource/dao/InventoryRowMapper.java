package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Inventory;
import com.juhrig.bricktool.datasource.dto.InventoryImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryRowMapper implements RowMapper<Inventory> {
    @Override
    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inventory result = null;
        if(rs != null){
            InventoryImpl inventory = new InventoryImpl();
            inventory.setInventoryId(rs.getInt("inventory_id"));
            inventory.setVersion(rs.getInt("version_number"));
            inventory.setSetNumber(rs.getString("set_number"));
        }
        return result;
    }
}
