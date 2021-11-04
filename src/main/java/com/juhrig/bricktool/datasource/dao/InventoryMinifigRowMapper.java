package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventoryMinifig;
import com.juhrig.bricktool.datasource.dto.InventoryMinifigImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMinifigRowMapper implements RowMapper<InventoryMinifig> {
    @Override
    public InventoryMinifig mapRow(ResultSet rs, int rowNum) throws SQLException {
        InventoryMinifigImpl result = null;
        if(rs != null){
            result = new InventoryMinifigImpl();
            result.setId(rs.getLong("id"));
            result.setMinifigNumber(rs.getString("minifig_number"));
            result.setInventoryId(rs.getInt("set_inventory_id"));
            result.setQuantity(rs.getInt("minifig_quantity"));
        }

        return result;
    }
}
