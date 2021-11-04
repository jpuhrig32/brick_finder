package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SetDaoImpl implements SetDao{

    JdbcTemplate daoTemplate;
    @Override
    public void setDataSource(JdbcTemplate template) {
        daoTemplate = template;
    }

    @Override
    public List<Set> listAll() {
        String sql = "SELECT * FROM part_set";
        return daoTemplate.query(sql, new SetRowMapper());
    }

    @Override
    public List<Set> findSetsByIdExact(String id) {
       String sql = "SELECT * FROM part_set WHERE set_number = (?)";
       return queryBySetId(id, sql);
    }

    @Override
    public List<Set> findSetsByIdSimilar(String id) {
        String sql = "SELECT * FROM part_set WHERE set_number LIKE (?)";
        return queryBySetId(id, sql);
    }

    @Override
    public List<String> listAllSetIds() {
        String sql = "SELECT set_number FROM part_set";
        List<String> ids = daoTemplate.query(
                sql,
                (resultSet, i) -> resultSet != null ? resultSet.getString("set_number") : ""
                )
                .stream()
                .filter(s -> StringUtils.isNotEmpty(s))
                .collect(Collectors.toList());
        return ids;
    }

    @Override
    public List<Set> findSetsByName(String name) {
        String sql = "SELECT * FROM part_set" +
                "WHERE SIMILARITY(" +
                "DMETAPHONE(?)," +
                "DMETAPHONE(name)" +
                ") > 0.4";

        List<Set> setsByName = daoTemplate.query(
                sql,
                preparedStatement -> preparedStatement.setString(1, name),
                new SetRowMapper());
        return setsByName;
    }

    @Override
    public void saveAll(List<Set> toSave) {
        String sql = "INSERT INTO part_set(" +
                "set_number," +
                "set_name," +
                "release_year," +
                "set_theme_id," +
                "part_count," +
                "set_theme" +
                ")" +
                "VALUES(?,?,?,?,?,?)" +
                "ON CONFLICT(set_number)" +
                "DO NOTHING";
        daoTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setString(1, argument.getSetNumber());
                    ps.setString(2, argument.getName());
                    ps.setInt(3, argument.getYear());
                    ps.setInt(4, argument.getThemeId());
                    ps.setInt(5, argument.getNumParts());
                    ps.setInt(6, argument.getThemeId());
                }));

    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM part_set";
        daoTemplate.execute(sql);
    }

    private List<Set> queryBySetId(String id, String query){
        return daoTemplate.query(query,
                ps -> ps.setString(1, id),
                new SetRowMapper()
        );
    }

}
