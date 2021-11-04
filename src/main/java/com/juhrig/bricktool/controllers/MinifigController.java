package com.juhrig.bricktool.controllers;

import com.juhrig.bricktool.datasource.dao.MinifigDao;
import com.juhrig.bricktool.datasource.dto.Minifig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/minifigs")
public class MinifigController {

    @Autowired
    MinifigDao minifigDao;

    @GetMapping(path="/byId/{id}", produces="application/json")
    public Minifig getMinifig(@PathVariable String id){
        List<Minifig> resultMinifigs = minifigDao.getMinifigByIdExact(id);
        if(resultMinifigs.size() > 0){
            return resultMinifigs.get(0);
        }
        return null;
    }

    @GetMapping(path="/byName/{name}", produces="application/json")
    public List<Minifig> getMinifigByName(@PathVariable String name){
        return minifigDao.findMinifigByName(name);
    }

}
