package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventoryMinifig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMinifigRepository extends CrudRepository<InventoryMinifig, Integer> {
}
