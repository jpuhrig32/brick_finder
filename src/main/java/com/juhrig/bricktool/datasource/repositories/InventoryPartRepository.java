package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventoryPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryPartRepository extends JpaRepository<InventoryPart, String> {

    @Query(value="Select p from InventoryPart p where p.partNumber in inventoryIdNumbers", nativeQuery = true)
    List<InventoryPart> getAllPartsByInventoryIds(@Param("inventoryIdNumbers") List<Integer> inventoryIdNumbers);

}
