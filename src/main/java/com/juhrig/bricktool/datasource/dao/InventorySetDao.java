package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.InventorySet;

import java.util.List;

public interface InventorySetDao extends DaoTemplate<InventorySet> {

     List<InventorySet> findInventorySetsBySetNumber(String setNumber);

}
