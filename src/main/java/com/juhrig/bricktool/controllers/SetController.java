package com.juhrig.bricktool.controllers;

import com.juhrig.bricktool.datasource.repositories.SetRepository;
import com.juhrig.bricktool.dto.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bricksets")
public class SetController {

    @Autowired
    SetRepository repository;

    @GetMapping(path="/{id}", produces="application/json")
    public Set getSet(@PathVariable String id){
        Set retSet = repository.getById(id);
        return retSet;
    }

}
