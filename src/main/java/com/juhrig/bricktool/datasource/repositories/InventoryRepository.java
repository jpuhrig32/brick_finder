package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.Inventory;
import com.juhrig.bricktool.datasource.dto.InventorySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(value="SELECT t from Inventory t where t.setNumber = 1?", nativeQuery = true)
    List<InventorySet> getInventoriesBySet(String setNumber);

}
