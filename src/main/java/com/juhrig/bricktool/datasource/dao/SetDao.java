package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Set;

import java.util.List;

public interface SetDao extends DaoTemplate<Set> {

     List<Set> findSetsByIdExact(String id);

     List<Set> findSetsByIdSimilar(String id);

     List<String> listAllSetIds();

     List<Set> findSetsByName(String name);


}
