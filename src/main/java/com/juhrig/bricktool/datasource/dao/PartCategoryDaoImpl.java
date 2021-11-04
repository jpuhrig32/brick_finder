package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.PartCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartCategoryDaoImpl implements PartCategoryDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<PartCategory> listAll() {
        String sql = "SELECT * FROM part_category";
        List<PartCategory> categories = jdbcTemplate.query(
                sql,
                new PartCategoryRowMapper()
        );
        return categories;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM part_category";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<PartCategory> toSave){
        String sql = "INSERT INTO part_category(" +
                "category_id," +
                "category_name)" +
                "VALUES (?,?)" +
                "ON CONFLICT(category_id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setInt(1, argument.getPartCategoryId());
                    ps.setString(2, argument.getCategoryName());
                }));
    }
}
