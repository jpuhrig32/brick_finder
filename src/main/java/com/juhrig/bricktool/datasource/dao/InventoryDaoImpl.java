package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryDaoImpl implements InventoryDao{

    @Autowired
    JdbcTemplate daoTemplate;

    @Override
    public void setDataSource(JdbcTemplate template) {
        daoTemplate = template;
    }

    @Override
    public List<Inventory> listAll() {
        String sql = "SELECT * FROM inventory";
       return daoTemplate.query(sql, new InventoryRowMapper());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM inventory";
        daoTemplate.execute(sql);
    }

    @Override
    public void saveAll(List<Inventory> toSave) {
        String sql = "INSERT INTO inventory (" +
                "inventory_id," +
                "version_number," +
                "set_number," +
                "parent_set_number" +
                ")" +
                "VALUES(?,?,?,?)" +
                "ON CONFLICT(inventory_id)" +
                "DO NOTHING";
        daoTemplate.batchUpdate(sql,
                toSave,
                toSave.size(),
                ((ps, argument) -> {
                    ps.setInt(1, argument.getInventoryId());
                    ps.setInt(2, argument.getVersion());
                    ps.setString(3, argument.getSetNumber());
                    ps.setString(4, argument.getSetNumber());
                }));

    }
}
