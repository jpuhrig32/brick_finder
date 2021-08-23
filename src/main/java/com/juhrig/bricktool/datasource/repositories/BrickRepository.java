package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Brick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrickRepository extends CrudRepository<Brick, String> {

}
