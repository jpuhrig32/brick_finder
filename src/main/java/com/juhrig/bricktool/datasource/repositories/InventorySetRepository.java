package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.InventorySetImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventorySetRepository extends JpaRepository<InventorySetImpl, Integer> {

    @Query(value="SELECT t from InventorySet t where t.setNumber = 1?", nativeQuery = true)
    List<InventorySetImpl> getInventorySetsBySet(String setNumber);
}
