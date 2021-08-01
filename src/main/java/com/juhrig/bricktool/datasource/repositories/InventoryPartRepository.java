package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.InventoryPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryPartRepository extends JpaRepository<InventoryPart, Integer> {
}
