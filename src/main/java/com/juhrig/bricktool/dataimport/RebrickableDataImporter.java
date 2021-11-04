package com.juhrig.bricktool.dataimport;

import com.juhrig.bricktool.datasource.dao.*;
import com.juhrig.bricktool.datasource.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RebrickableDataImporter {
    
    public static final String BRICK = "brick";
    public static final String COLOR = "color";
    public static final String ELEMENT = "element";
    public static final String INVENTORY = "inventory";
    public static final String INVENTORY_MINIFIG = "inventory_minifig";
    public static final String INVENTORY_PART = "inventory_part";
    public static final String INVENTORY_SET = "inventory_set";
    public static final String MINIFIG = "minifig";
    public static final String PART = "part";
    public static final String PART_CATEGORY = "part_category";
    public static final String PART_RELATIONSHIP = "part_relationship";
    public static final String SET = "set";
    public static final String THEME = "theme";


    private static final Logger LOG = LoggerFactory.getLogger(RebrickableDataImporter.class);

    @Autowired
    ColorDao colorRepository;

    @Autowired
    ElementDao elementRepository;

    @Autowired
    InventoryDao inventoryRepository;

    @Autowired
    InventoryMinifigDao inventoryMinifigRepository;

    @Autowired
    InventoryPartDao inventoryPartRepository;

    @Autowired
    InventorySetDao inventorySetRepository;

    @Autowired
    MinifigDao minifigRepository;

    @Autowired
    PartDao partRepository;

    @Autowired
    PartCategoryDao partCategoryRepository;

    @Autowired
    PartRelationshipDao partRelationshipRepository;

    @Autowired
    SetDao setRepository;
    
    @Autowired 
    ThemeDao themeRepository;


    public void importRebrickableData(Map<String, InputStream> rebrickableFiles){
        importRebrickableData(rebrickableFiles, false);
    }

    public void importRebrickableData(Map<String, InputStream> rebrickableFiles, boolean purge){
        if(purge){
            colorRepository.deleteAll();
            elementRepository.deleteAll();
            inventoryRepository.deleteAll();
            inventoryMinifigRepository.deleteAll();
            inventoryPartRepository.deleteAll();
            inventorySetRepository.deleteAll();
            minifigRepository.deleteAll();
            partRepository.deleteAll();
            partRelationshipRepository.deleteAll();
            setRepository.deleteAll();
            themeRepository.deleteAll();
        }
        for(Map.Entry<String, InputStream> entry : rebrickableFiles.entrySet()){
            if(entry.getValue() == null){
                LOG.warn(String.format("Entry %s had a null InputStream", entry.getKey()));
                continue;
            }
            List<String[]> lines = readCsvLines(entry.getValue());
            if(lines.size() == 0){
                continue;
            }
            switch(entry.getKey()){
                case COLOR:
                    List<Color> colors = readColorCsv(lines);
                    colorRepository.saveAll(colors);
                    break;
                case ELEMENT:
                    List<Element> elements = readElementCsv(lines);
                    elementRepository.saveAll(elements);
                    break;
                case INVENTORY:
                    List<Inventory> inventories = readInventoryCsv(lines);
                    inventoryRepository.saveAll(inventories);
                    break;
                case INVENTORY_MINIFIG:
                    List<InventoryMinifig> inventoryMinifigs = readInventoryMinifigCsv(lines);
                    inventoryMinifigRepository.saveAll(inventoryMinifigs);
                    break;
                case INVENTORY_PART:
                    List<InventoryPart> inventoryParts = readInventoryPartCsv(lines);
                    inventoryPartRepository.saveAll(inventoryParts);
                    break;
                case INVENTORY_SET:
                    List<InventorySet> inventorySets = readInventorySetCsv(lines);
                    inventorySetRepository.saveAll(inventorySets);
                    break;
                case MINIFIG:
                    List<Minifig> minifigs = readMinifigCsv(lines);
                    minifigRepository.saveAll(minifigs);
                    break;
                case PART:
                    List<Part> parts = readPartCsv(lines);
                    partRepository.saveAll(parts);
                    break;
                case PART_CATEGORY:
                    List<PartCategory> categories = readPartCategoryCsv(lines);
                    partCategoryRepository.saveAll(categories);
                    break;
                case PART_RELATIONSHIP:
                    List<PartRelationship> partRelationships = readPartRelationshipCsv(lines);
                    partRelationshipRepository.saveAll(partRelationships);
                    break;
                case SET:
                    List<Set> sets = readSetCsv(lines);
                    setRepository.saveAll(sets);
                    break;
                case THEME:
                    List<Theme> themes = readThemeCsv(lines);
                    themeRepository.saveAll(themes);
                    break;
                default:
                    LOG.warn(String.format("The data file tagged as %s is not a valid tag", entry.getKey()));
                    break;
            }
        }
    }


    protected List<Color> readColorCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 4)
                .map(line ->{
                    return new ColorImpl(Integer.parseInt(line[0]),line[1],line[2], Boolean.parseBoolean(line[3]));
                })
                .collect(Collectors.toList());
    }

    protected List<Element> readElementCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new ElementImpl(line[0],line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Inventory> readInventoryCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new InventoryImpl(Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[2]);
                })
                .collect(Collectors.toList());
    }

    protected List<InventoryMinifig> readInventoryMinifigCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new InventoryMinifigImpl(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }
    protected List<InventoryPart> readInventoryPartCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 5)
                .map(line ->{
                    return new InventoryPartImpl(Integer.parseInt(line[0]),line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Boolean.parseBoolean(line[4]));
                })
                .collect(Collectors.toList());
    }

    protected List<InventorySet> readInventorySetCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new InventorySetImpl(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Minifig> readMinifigCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new MinifigImpl(line[0],line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Part> readPartCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 4)
                .map(line ->{
                    return new PartImpl(line[0], line[1],Integer.parseInt(line[2]), line[3]);
                })
                .collect(Collectors.toList());
    }

    protected List<PartCategory> readPartCategoryCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 2)
                .map(line ->{
                    return new PartCategoryImpl(Integer.parseInt(line[0]), line[1]);
                })
                .collect(Collectors.toList());
    }

    protected List<PartRelationship> readPartRelationshipCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new PartRelationshipImpl(line[0], line[1], line[2]);
                })
                .collect(Collectors.toList());
    }

    protected List<Set> readSetCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 5)
                .map(line ->{
                    return new SetImpl(line[0], line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]));
                })
                .collect(Collectors.toList());
    }

    protected List<Theme> readThemeCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new ThemeImpl(Integer.parseInt(line[0]),line[1], ImportUtils.parseIntegerOrNull(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<String[]> readCsvLines(InputStream fileInput){
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInput));
            List<String[]> lines = br.lines()
                    .map(line -> {
                        if(line.length() > 0) {
                            List<String> sl = ImportUtils.splitLineOnDelimiter(line, ',');
                            return sl.toArray(new String[sl.size()]);
                        }
                        else{
                            return new String[0];
                        }
                    })
                    .filter(line -> line.length > 0)
                    .collect(Collectors.toList());
            if(lines.size() > 0) {
                lines.remove(0);
            }
            return lines;
    }






}
