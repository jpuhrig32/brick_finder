package com.juhrig.bricktool.datasource.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SequenceUtils {

    public static List<Long> fetchNextIds(
            JdbcTemplate jdbcTemplate,
            String sequence,
            long offset,
            boolean rangeExclusive) {
        String fetchQuery = "SELECT nextval(?)";
        Long id = jdbcTemplate.query(
                fetchQuery,
                (PreparedStatementSetter) ps ->{
                    ps.setString(1, sequence);
                },
                (ResultSetExtractor<Long>) rs -> {
                    return rs.getLong(1);
                });
        String setQuery = "SELECT setval(?,?,?)";
        jdbcTemplate.execute(setQuery,
                (PreparedStatementCallback<Boolean>) ps -> {
                    ps.setString(1, sequence);
                    ps.setLong(2, id+offset);
                    ps.setBoolean(3, rangeExclusive);
                    return ps.execute();
                });
        List<Long> ids = LongStream.range(id, id+offset).mapToObj(
                l -> l).collect(Collectors.toList());
        return ids;
    }

}
