package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventorySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventorySetRepository extends JpaRepository<InventorySet, Integer> {

    @Query(value="SELECT t from InventorySet t where t.setNumber = 1?", nativeQuery = true)
    List<InventorySet> getInventorySetsBySet(String setNumber);
}
