package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Color;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface ColorDao extends DaoTemplate<Color> {

    void setDataSource(JdbcTemplate template);

    Color getColorById(String id);

    @Override
    void saveAll(List<Color> toSave);
}
