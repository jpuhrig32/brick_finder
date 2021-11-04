package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.Element;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends CrudRepository<Element, Integer> {
}
