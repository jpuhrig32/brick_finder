package com.juhrig.bricktool.datasource.dao;

import com.juhrig.bricktool.datasource.dto.Minifig;

import java.util.List;

public interface MinifigDao extends DaoTemplate<Minifig>{

    List<Minifig> findMinifigByName(String name);

    List<Minifig> getMinifigByIdExact(String id);
}
