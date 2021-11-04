package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.DatabaseVersion;
import com.juhrig.bricktool.datasource.dto.DatabaseVersionImpl;
import com.juhrig.bricktool.error.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseVersionDaoImpl implements DatabaseVersionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public DatabaseVersion getCurrentVersion(){
        String sql = "SELECT DISTINCT ON (version_number)" +
                "id" +
                "version_number," +
                "version_date" +
                "FROM version" +
                "ORDER BY version_number DESC";

        List<DatabaseVersion> results = jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    DatabaseVersion value = null;
                    if(resultSet != null){
                        value = new DatabaseVersionImpl();
                        ((DatabaseVersionImpl)value).setId(resultSet.getInt("id"));
                        ((DatabaseVersionImpl)value).setId(resultSet.getInt("version_number"));
                        ((DatabaseVersionImpl)value).setVersionDate(resultSet.getDate("version_date"));
                    }
                    return value;
                });
        if(results.size() < 1){
            throw new DatabaseException("Error - Could not find database version");
        }
        return results.get(0);
    }

    @Override
    public void writeDatabaseVersion(DatabaseVersion currentVersion) {
        String sql = "INSERT INTO version (" +
                "version_number," +
                "version_date" +
                ")" +
                "VALUES (?,?)" +
                "ON CONFLICT(version_number)" +
                "DO NOTHING";
        jdbcTemplate.update(
                sql,
                currentVersion.getVersionNumber(),
                currentVersion.getVersionDate()
        );
    }
}
