package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventoryPart;
import com.juhrig.bricktool.datasource.util.SequenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryPartDaoImpl implements InventoryPartDao{

    private static final int DEFAULT_INCREMENT = 20;
    private static final String SEQUENCE = "part_relationship_seq";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    List<Long> idList;
    int incrementBy;
    int idPosition;

    public InventoryPartDaoImpl(){
        this(DEFAULT_INCREMENT);
    }

    public InventoryPartDaoImpl(int incrementBy){
        this.incrementBy = incrementBy;
        this.idPosition = 0;
        idList = null;
    }

    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<InventoryPart> listAll() {
        String sql = "SELECT * FROM inventory_part";
        List<InventoryPart> parts = jdbcTemplate.query(sql, new InventoryPartRowMapper());
        //parts = parts.stream().filter(n -> n != null).collect(Collectors.toList());
        return parts;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM inventory_part";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<InventoryPart> toSave) {
        checkAndAssignIds(toSave);
        String sql = "INSERT INTO inventory_part(" +
                "part_number," +
                "inventory_id," +
                "color_id," +
                "quantity," +
                "is_spare_part" +
                ")" +
                "VALUES (?,?,?,?,?,?)" +
                "ON CONFLICT(id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setString(1, argument.getPartNumber());
                    ps.setInt(2, argument.getInventoryId());
                    ps.setInt(3, argument.getColorId());
                    ps.setInt(4, argument.getQuantity());
                    ps.setBoolean(5, argument.isSpare());
                }));
    }

    private void checkAndAssignId(InventoryPart toSave){
        if(toSave.getId() <= 0){
            toSave.setId(getNextId());
        }
    }
    private void checkAndAssignIds(List<InventoryPart> toSave){
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
