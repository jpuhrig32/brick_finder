package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Brick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrickInventory extends JpaRepository<Brick, Integer> {

    @Query("SELECT t from Brick t where t.partNum= ?1")
    Brick findByPartNumber(String partNum);

    @Query("SELECT t from Brick t where t.partNum in :partNumbers")
    List<Brick> findByPartNumbers(@Param("partNumbers") List<String> partNumbers);


}
