package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Minifig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinifigRepository extends JpaRepository<Minifig, String> {
}
