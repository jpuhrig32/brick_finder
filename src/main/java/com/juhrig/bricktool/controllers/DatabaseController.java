package com.juhrig.bricktool.controllers;

import com.juhrig.bricktool.dataimport.RebrickableDataImporter;
import com.juhrig.bricktool.datasource.repositories.SetRepository;
import com.juhrig.bricktool.datasource.dto.SetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/databaseControl")
public class DatabaseController {

    @Autowired
    SetRepository setRepository;

    @Autowired
    RebrickableDataImporter rbi;

    Map<String, InputStream> rebrickableFiles;

    public DatabaseController(){
        rebrickableFiles = new HashMap<>();
        try {
            rebrickableFiles.put(RebrickableDataImporter.SET, new FileInputStream(Paths.get("./resources/static/sets.csv").toFile()));
            //InputStream setFile = new FileInputStream(Paths.get("./resources/static/sets.csv").toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("purgeandreload")
    public List<SetImpl> purgeAndReloadDatabase(){
        Map<String, InputStream> inputFileMap = new LinkedHashMap<>();
        try {
            inputFileMap.put(
                    RebrickableDataImporter.COLOR,
                    new FileInputStream(Paths.get("./resources/static/colors.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.MINIFIG,
                    new FileInputStream(Paths.get("./resources/static/minifigs.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.PART_CATEGORY,
                    new FileInputStream(Paths.get("./resources/static/part_categories.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.PART,
                    new FileInputStream(Paths.get("./resources/static/parts.csv").toFile())
            );
            /*
            inputFileMap.put(
                    RebrickableDataImporter.PART_RELATIONSHIP,
                    new FileInputStream(Paths.get("./resources/static/part_relationships.csv").toFile())
            );

             */
            inputFileMap.put(
                    RebrickableDataImporter.THEME,
                    new FileInputStream(Paths.get("./resources/static/themes.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.ELEMENT,
                    new FileInputStream(Paths.get("./resources/static/elements.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.INVENTORY,
                    new FileInputStream(Paths.get("./resources/static/inventories.csv").toFile())
            );

            inputFileMap.put(
                    RebrickableDataImporter.SET,
                    new FileInputStream(Paths.get("./resources/static/sets.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.INVENTORY_SET,
                    new FileInputStream(Paths.get("./resources/static/inventory_sets.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.INVENTORY_MINIFIG,
                    new FileInputStream(Paths.get("./resources/static/inventory_minifigs.csv").toFile())
            );
            inputFileMap.put(
                    RebrickableDataImporter.INVENTORY_PART,
                    new FileInputStream(Paths.get("./resources/static/inventory_parts.csv").toFile())
            );


        }
        catch(IOException e){
            e.printStackTrace();
        }
            rbi.importRebrickableData(inputFileMap, true);

            return setRepository.findAll();

    }
}
