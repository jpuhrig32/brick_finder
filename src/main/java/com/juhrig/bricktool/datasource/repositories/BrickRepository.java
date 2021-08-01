package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Brick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrickRepository extends JpaRepository<Brick, String> {

}
