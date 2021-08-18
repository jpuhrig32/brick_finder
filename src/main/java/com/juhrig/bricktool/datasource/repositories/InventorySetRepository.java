package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventorySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventorySetRepository extends JpaRepository<InventorySet, Integer> {

    @Query(value="SELECT t from InventorySet t where t.setNumber = 1?")
    List<InventorySet> getInventorySetsBySet(String setNumber);
}
