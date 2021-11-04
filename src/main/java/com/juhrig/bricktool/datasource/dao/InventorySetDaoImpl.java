package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventorySet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InventorySetDaoImpl implements InventorySetDao{

    JdbcTemplate daoTemplate;
    @Override
    public void setDataSource(JdbcTemplate template) {
        daoTemplate = template;
    }

    @Override
    public List<InventorySet> listAll() {
        String sql = "SELECT * FROM inventory_set";
        return daoTemplate.query(sql, new InventorySetRowMapper());
    }


    @Override
    public List<InventorySet> findInventorySetsBySetNumber(String setNumber) {
        String sql = "SELECT * FROM inventory_set WHERE set_number = (?)";
        return daoTemplate.query(sql,
                ps -> ps.setString(1, setNumber),
                new InventorySetRowMapper());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM inventory_set";
        daoTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<InventorySet> toSave) {
        String sql = "INSERT INTO inventory_set (" +
                "inventory_id," +
                "set_number," +
                "set_quantity," +
                "revision_number," +
                ")" +
                "VALUES (?,?,?,?)" +
                "ON CONFLICT(inventory_id)" +
                "DO NOTHING";
        daoTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                (ps, argument) ->{
                    ps.setInt(1, argument.getInventoryId());
                    ps.setString(2, argument.getSetNumber());
                    ps.setInt(3, argument.getQuantity());
                    ps.setInt(4, argument.getRevision());
                });
    }
}
