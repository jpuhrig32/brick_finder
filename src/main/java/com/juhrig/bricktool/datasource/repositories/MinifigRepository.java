package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.MinifigImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinifigRepository extends JpaRepository<MinifigImpl, String> {
}
