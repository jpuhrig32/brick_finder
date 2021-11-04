package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.PartCategory;
import com.juhrig.bricktool.datasource.dto.PartCategoryImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartCategoryRowMapper implements RowMapper<PartCategory> {
    @Override
    public PartCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartCategoryImpl result = null;
        if(rs != null){
            result = new PartCategoryImpl();
            result.setPartCategoryId(rs.getInt("category_id"));
            result.setCategoryName(rs.getString("category_name"));
        }
        return result;
    }
}
