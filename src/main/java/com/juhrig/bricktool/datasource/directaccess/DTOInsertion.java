package com.juhrig.bricktool.datasource.directaccess;

import com.juhrig.bricktool.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class DTOInsertion {

    @Autowired
    JdbcTemplate daoTemplate;




    public void insertColorObjects(List<Color> colorList){
        String insertStatement = "INSERT into color_entity (color_id, color_name, color_rgb, is_transparent) VALUES (?,?,?,?)";
        daoTemplate.batchUpdate(insertStatement, colorList, colorList.size(), new ParameterizedPreparedStatementSetter<Color>() {
            @Override
            public void setValues(PreparedStatement ps, Color argument) throws SQLException {
                ps.setInt(1, argument.getColorId());
                ps.setString(2, argument.getColorName());
                ps.setString(3, argument.getColorRGB());
                ps.setBoolean(4, argument.isTrans());
            }
        });
    }


    public void insertInventoryMinifigObjects(List<InventoryMinifig> inventoryMinifigs){
        String insertStatement = "INSERT INTO inventory_minifig(id, minifig_number, set_inventory_id, minifig_quantity) VALUES (nextval('inventory_minifig_id_seq'),?,?,?)";
        daoTemplate.batchUpdate(insertStatement, inventoryMinifigs, inventoryMinifigs.size(), new ParameterizedPreparedStatementSetter<InventoryMinifig>() {
            @Override
            public void setValues(PreparedStatement ps, InventoryMinifig argument) throws SQLException {
                ps.setString(1, argument.getMinifigNumber());
                ps.setInt(2, argument.getInventoryId());
                ps.setInt(3, argument.getQuantity());
            }
        });
    }

    public void insertInventoryPartObjects(List<InventoryPart> inventoryParts){
        String insertStatement = "INSERT " +
                "INTO inventory_part(" +
                "id" +
                ",part_number" +
                ",inventory_id" +
                ",color_id" +
                ",quantity" +
                ",is_spare_part) " +
                "VALUES(nextval('inventory_part_id_seq'),?,?,?,?,?)";

        daoTemplate.batchUpdate(insertStatement, inventoryParts, inventoryParts.size(), new ParameterizedPreparedStatementSetter<InventoryPart>() {
            @Override
            public void setValues(PreparedStatement ps, InventoryPart argument) throws SQLException {
                ps.setString(1, argument.getPartNumber());
                ps.setInt(2, argument.getInventoryId());
                ps.setInt(3, argument.getColorId());
                ps.setInt(4, argument.getQuantity());
                ps.setBoolean(5, argument.isSpare());
            }
        });
    }

    public void insertPartRelationshipObjects(List<PartRelationship> partRelationships){
        String insertStatement = "INSERT INTO part_relationship(id, relationship_type, child_part_number, parent_part_number) VALUES(nextval('part_relationship_id_seq'),?,?,? )";
        daoTemplate.batchUpdate(insertStatement, partRelationships, partRelationships.size(), new ParameterizedPreparedStatementSetter<PartRelationship>() {
            @Override
            public void setValues(PreparedStatement ps, PartRelationship argument) throws SQLException {
                ps.setString(1, argument.getRelationshipType());
                ps.setString(2, argument.getChildPartNumber());
                ps.setString(3, argument.getParentPartNumber());
            }
        });
    }


}
