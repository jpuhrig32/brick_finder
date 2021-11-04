package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartDaoImpl implements PartDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<Part> listAll() {
        return null;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM part";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<Part> toSave) {
        String sql = "INSERT INTO part(" +
                "part_number," +
                "part_name," +
                "part_catalog_id," +
                "part_material" +
                ")" +
                "VALUES (?,?,?,?)" +
                "ON CONFILCT(part_number)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setString(1, argument.getPartNumber());
                    ps.setString(2, argument.getName());
                    ps.setInt(3, argument.getPartCategoryId());
                    ps.setString(4, argument.getPartMaterial());
                }));
    }
}
