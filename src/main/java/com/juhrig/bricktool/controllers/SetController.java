package com.juhrig.bricktool.controllers;

import com.juhrig.bricktool.datasource.dao.SetDao;
import com.juhrig.bricktool.datasource.dto.Set;
import com.juhrig.bricktool.datasource.repositories.InventoryPartRepository;
import com.juhrig.bricktool.datasource.repositories.InventorySetRepository;
import com.juhrig.bricktool.datasource.repositories.SetRepository;
import com.juhrig.bricktool.datasource.dto.InventorySetImpl;
import com.juhrig.bricktool.datasource.dto.SetImpl;
import org.atmosphere.config.service.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bricksets")
public class SetController {

    @Autowired
    SetDao setDao;

    @GetMapping(path="/byId/{id}", produces="application/json")
    public Set getSet(@PathVariable String id){
        List<Set> resultSets = setDao.findSetsByIdExact(id);
        if(resultSets.size() > 0){
            return resultSets.get(0);
        }
        return null;
    }

    @GetMapping(path="/listSets", produces="application/json")
    public List<String> getAllSetIds(){
        return setDao.listAllSetIds();
    }

    @GetMapping(path="/byName/{name}", produces="application/json")
    public List<Set> getSetByName(@PathVariable String name){
        return setDao.findSetsByName(name);
    }





}
