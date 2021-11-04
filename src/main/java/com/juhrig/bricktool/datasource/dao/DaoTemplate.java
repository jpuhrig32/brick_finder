package com.juhrig.bricktool.datasource.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface DaoTemplate<T> {

    void setDataSource(JdbcTemplate template);

    List<T> listAll();

    void deleteAll();

    void saveAll(List<T> toSave);
}
