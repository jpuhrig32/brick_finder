package com.juhrig.bricktool.controllers;

import com.juhrig.bricktool.dataimport.RebrickableDataImporter;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/databaseControl")
public class DatabaseController {

    @GetMapping("purgeandreload")
    public void purgeAndReloadDatabase(){
        RebrickableDataImporter rbi = new RebrickableDataImporter();
        Map<String, InputStream> inputFiles = Map.of(
                RebrickableDataImporter.COLOR,
                DatabaseController.class.getResourceAsStream("static/colors.csv"),
                RebrickableDataImporter.ELEMENT,
                DatabaseController.class.getResourceAsStream("static/elements.csv"),
                RebrickableDataImporter.INVENTORY,
                DatabaseController.class.getResourceAsStream("static/inventories.csv"),
                RebrickableDataImporter.INVENTORY_MINIFIG,
                DatabaseController.class.getResourceAsStream("static/inventory_minifigs.csv"),
                RebrickableDataImporter.INVENTORY_PART,
                DatabaseController.class.getResourceAsStream("static/inventory_parts.csv"),
                RebrickableDataImporter.INVENTORY_SET,
                DatabaseController.class.getResourceAsStream("static/inventory_sets.csv"),
                RebrickableDataImporter.MINIFIG,
                DatabaseController.class.getResourceAsStream("static/minifigs.csv"),
                RebrickableDataImporter.PART,
                DatabaseController.class.getResourceAsStream("static/parts.csv"),
                RebrickableDataImporter.PART_RELATIONSHIP,
                DatabaseController.class.getResourceAsStream("static/part_relationships.csv"),
                RebrickableDataImporter.SET,
                DatabaseController.class.getResourceAsStream("static/sets.csv")
        );
        inputFiles.put(
                RebrickableDataImporter.THEME,
                DatabaseController.class.getResourceAsStream("static/themes.csv")
        );
        rbi.importRebrickableData(inputFiles, true);
    }
}
