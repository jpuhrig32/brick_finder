package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.InventoryMinifig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMinifigRepository extends CrudRepository<InventoryMinifig, Integer> {

    @Query(value="Select m from InventoryMinifig m where m.minifigNumber in minifigIdNumbers", nativeQuery = true)
    List<InventoryMinifig> getAllMinifigsByInventoryIds(@Param("minifigIdNumbers") List<Integer> minifigIdNumbers);
}
