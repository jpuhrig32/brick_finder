package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Minifig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinifigDaoImpl implements MinifigDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<Minifig> listAll() {
        String sql = "SELECT * FROM minifig";
        List<Minifig> minifigs = jdbcTemplate.query(sql, new MinifigRowMapper());
        return minifigs;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM minifig";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<Minifig> toSave) {
        String sql = "INSERT INTO minifig(" +
                "minifig_id," +
                "minifig_name," +
                "part_count" +
                ")" +
                "VALUES (?,?,?)" +
                "ON CONFLICT(minifig_id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setString(1, argument.getMinifigId());
                    ps.setString(2, argument.getName());
                    ps.setInt(3, argument.getNumParts());
                }));
    }

    @Override
    public List<Minifig> findMinifigByName(String name) {
        String sql = "SELECT * from minifig" +
                "WHERE SIMILARITY(" +
                "DMETAPHONE(?)," +
                "DMETAPHONE(minifig_name)" +
                ") > 0.4";
        List<Minifig> minifigs = jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setString(1, name),
                new MinifigRowMapper()
        );
        return minifigs;
    }

    @Override
    public List<Minifig> getMinifigByIdExact(String id) {
        String sql = "SELECT DISTINCT ON(minifig_id) * from minifig" +
                "WHERE minifig_id = ?" +
                "LIMIT 1";
        List<Minifig> minifigs = jdbcTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setString(1, id),
                new MinifigRowMapper()
        );
        return minifigs;
    }
}
