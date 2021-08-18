package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Inventory;
import com.juhrig.bricktool.dto.InventorySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(value="SELECT t from Inventory t where t.setNumber = 1?")
    List<InventorySet> getInventoriesBySet(String setNumber);

}
