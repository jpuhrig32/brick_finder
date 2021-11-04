package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Element;
import com.juhrig.bricktool.datasource.dto.ElementImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementRowMapper implements RowMapper<Element> {
    @Override
    public Element mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElementImpl result = null;
        if(rs != null){
            result = new ElementImpl();
            result.setElementId(rs.getString("element_id"));
            result.setPartNum(rs.getString("part_num"));
            result.setColorId(rs.getInt("color_id"));
        }
        return result;
    }
}
