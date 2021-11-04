package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementDaoImpl implements ElementDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<Element> listAll() {
        String sql = "SELECT * FROM element";
        List<Element> elements = jdbcTemplate.query(
                sql,
                new ElementRowMapper()
        );
        return elements;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM element";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<Element> toSave) {
        String sql = "INSERT INTO element (" +
                "element_id," +
                "part_num," +
                "color_id" +
                ")" +
                "VALUES (?,?,?)" +
                "ON CONFLICT(element_id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setString(1, argument.getElementId());
                    ps.setString(2, argument.getPartNum());
                    ps.setInt(3, argument.getColorId());
                })
        );
    }
}
