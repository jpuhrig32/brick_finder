package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorDaoImpl implements ColorDao {

    @Autowired
    JdbcTemplate daoTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        daoTemplate = template;
    }


    @Override
    public Color getColorById(String id) {
        return null;
    }

    @Override
    public List<Color> listAll() {
        String sql = "SELECT * FROM color_entity";
        return daoTemplate.query(sql, new ColorRowMapper());
    }

    @Override
    public void saveAll(List<Color> toSave) {
        String sql = "INSERT INTO color_entity (" +
                "color_id," +
                "color_name," +
                "color_rgb," +
                "is_transparent" +
                ")" +
                "VALUES (?,?,?,?)" +
                "ON_CONFLICT(color_id)" +
                "DO NOTHING";
        daoTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                (ps, argument) ->{
                    ps.setInt(1, argument.getColorId());
                    ps.setString(2, argument.getColorName());
                    ps.setString(3, argument.getColorRGB());
                    ps.setBoolean(4, argument.isTrans());
                });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM color_entity";
        daoTemplate.execute(sql);
    }

}
