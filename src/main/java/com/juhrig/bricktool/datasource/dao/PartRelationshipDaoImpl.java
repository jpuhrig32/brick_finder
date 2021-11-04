package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventoryMinifig;
import com.juhrig.bricktool.datasource.dto.PartRelationship;
import com.juhrig.bricktool.datasource.util.SequenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class PartRelationshipDaoImpl implements PartRelationshipDao {

    private static final int DEFAULT_INCREMENT = 20;
    private static final String SEQUENCE = "part_relationship_seq";

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Long> idList;
    int incrementBy;
    int idPosition;

    public PartRelationshipDaoImpl(){
        this(DEFAULT_INCREMENT);
    }

    public PartRelationshipDaoImpl(int idIncrementCount){
        this.incrementBy = idIncrementCount;
        this.idList = null;
        this.idPosition = 0;
    }

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<PartRelationship> listAll() {
        String sql = "SELECT * FROM part_relationship";
        List<PartRelationship> partRelationships = jdbcTemplate.query(
                sql,
                new PartRelationshipRowMapper()
        );
        return partRelationships;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM part_relationship";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<PartRelationship> toSave) {
        checkAndAssignIds(toSave);
        String sql = "INSERT INTO part_relationship(" +
                "id" +
                "relationship_type," +
                "child_part_number," +
                "parent_part_number" +
                ")" +
                "VALUES (?,?,?,?)" +
                "ON CONFLICT(id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setLong(1, argument.getId());
                    ps.setString(2, argument.getRelationshipType());
                    ps.setString(3, argument.getChildPartNumber());
                    ps.setString(4, argument.getParentPartNumber());
                }));
    }

    private void checkAndAssignId(PartRelationship toSave){
        if(toSave.getId() <= 0){
            toSave.setId(getNextId());
        }
    }
    private void checkAndAssignIds(List<PartRelationship> toSave){
        toSave.forEach(
                partRelationship -> checkAndAssignId(partRelationship)
        );
    }

    private Long getNextId(){
        if(idList == null || idPosition == idList.size()){
            idPosition = 0;
            idList = SequenceUtils.fetchNextIds(
                    jdbcTemplate,
                    SEQUENCE,
                    incrementBy,
                    false
            );
        }
        return idList.get(idPosition++);
    }
}
