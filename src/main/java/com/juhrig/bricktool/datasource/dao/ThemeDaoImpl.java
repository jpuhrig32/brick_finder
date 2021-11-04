package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;

@Service
public class ThemeDaoImpl implements ThemeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<Theme> listAll() {
        String sql = "SELECT * FROM theme";
        List<Theme> themes = jdbcTemplate.query(
                sql,
                new ThemeRowMapper()
        );
        return themes;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM theme";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<Theme> toSave) {
        String sql = "INSERT INTO theme(" +
                "theme_id," +
                "theme_name," +
                "parent_theme_id" +
                ")" +
                "VALUES (?,?,?)" +
                "ON CONFLICT(theme_id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setInt(1, argument.getThemeId());
                    ps.setString(2, argument.getThemeName());
                    int parentId = argument.getParentId();
                    if(parentId < 0){
                        ps.setNull(3, Types.INTEGER);
                    }
                    else {
                        ps.setInt(3, argument.getParentId());
                    }
                }));
    }
}
