package com.juhrig.bricktool.datasource.dataaccess;

import com.juhrig.bricktool.datasource.dao.InventorySetDao;
import com.juhrig.bricktool.datasource.dao.SetDao;
import com.juhrig.bricktool.datasource.repositories.InventoryMinifigRepository;
import com.juhrig.bricktool.datasource.repositories.InventoryPartRepository;
import com.juhrig.bricktool.datasource.repositories.InventorySetRepository;
import com.juhrig.bricktool.datasource.repositories.PartRepository;
import com.juhrig.bricktool.datasource.dto.InventorySet;
import com.juhrig.bricktool.datasource.dto.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetMapper {

    @Autowired
    InventorySetRepository inventorySetRepository;

    @Autowired
    InventoryPartRepository inventoryPartRepository;

    @Autowired
    InventoryMinifigRepository inventoryMinifigRepository;

    @Autowired
    PartRepository partRepository;

    @Autowired
    JdbcTemplate daoTemplate;

    @Autowired
    SetDao setData;

    @Autowired
    InventorySetDao inventorySetData;

    public List<Set> findSetsById(String id){
        List<Set> sets = setData.findSetsByIdSimilar(id);
        return sets;
    }

    public List<InventorySet> findInventorySetsById(String setId){
        List<InventorySet> inventorySets = inventorySetData.findInventorySetsBySetNumber(setId);
        return inventorySets;
    }
}
