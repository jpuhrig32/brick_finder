package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventoryMinifig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMinifigRepository extends JpaRepository<InventoryMinifig, Integer> {
}
