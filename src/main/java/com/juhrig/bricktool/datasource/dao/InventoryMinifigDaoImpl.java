package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventoryMinifig;
import com.juhrig.bricktool.datasource.util.SequenceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMinifigDaoImpl implements InventoryMinifigDao{

    private static final int DEFAULT_INCREMENT = 20;
    private static final String SEQUENCE = "inventory_minifig_seq";

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Long> idList;
    int incrementBy;
    int idPosition;

    public InventoryMinifigDaoImpl(){
        this(DEFAULT_INCREMENT);
    }

    public InventoryMinifigDaoImpl(int incrementSize){
        this.incrementBy = incrementSize;
        idList = null;
        idPosition = 0;
    }
    @Override
    public void setDataSource(JdbcTemplate template) {
        jdbcTemplate = template;
    }

    @Override
    public List<InventoryMinifig> listAll() {
        String sql = "SELECT * FROM inventory_minifig";
        List<InventoryMinifig> inventoryMinifigs = jdbcTemplate.query(
                sql,
                new InventoryMinifigRowMapper()
        );
        return inventoryMinifigs;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM inventory_minifig";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<InventoryMinifig> toSave) {
        checkAndAssignIds(toSave);
        String sql = "INSERT INTO inventory_minifig(" +
                "id," +
                "minifig_number," +
                "set_inventory_id," +
                "minifig_quantity," +
                "minifig_id" +
                ")" +
                "VALUES (?,?,?,?,?)" +
                "ON CONFLICT(id)" +
                "DO NOTHING";
        jdbcTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setLong(1, argument.getId());
                    ps.setString(2, argument.getMinifigNumber());
                    ps.setInt(3, argument.getInventoryId());
                    ps.setInt(4, argument.getQuantity());
                    ps.setString(5, argument.getMinifigNumber());
                }));
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

    private void checkAndAssignId(InventoryMinifig toSave){
        if(toSave.getId() <= 0){
            toSave.setId(getNextId());
        }
    }
    private void checkAndAssignIds(List<InventoryMinifig> toSave){
        toSave.forEach(
                inventoryMinifig -> checkAndAssignId(inventoryMinifig)
        );
    }

}
