package com.juhrig.bricktool.dataimport;

import com.juhrig.bricktool.datasource.repositories.*;
import com.juhrig.bricktool.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public static final String PART_RELATIONSHIP = "part_relationship";
    public static final String SET = "set";
    public static final String THEME = "theme";

    private static final Logger LOG = LoggerFactory.getLogger(RebrickableDataImporter.class);

    @Autowired
    BrickRepository brickRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ElementRepository elementRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryMinifigRepository inventoryMinifigRepository;

    @Autowired
    InventoryPartRepository inventoryPartRepository;

    @Autowired
    InventorySetRepository inventorySetRepository;

    @Autowired
    MinifigRepository minifigRepository;

    @Autowired
    PartRelationshipRepository partRelationshipRepository;

    @Autowired
    PartRepository partRepository;

    @Autowired
    SetRepository setRepository;
    
    @Autowired 
    ThemeRepository themeRepository;

    public void importRebrickableData(Map<String, InputStream> rebrickableFiles){
        importRebrickableData(rebrickableFiles, false);
    }

    public void importRebrickableData(Map<String, InputStream> rebrickableFiles, boolean purge){
        if(purge){
            brickRepository.deleteAll();
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
            List<String[]> lines = readCsvLines(entry.getValue());
            switch(entry.getKey()){
                case BRICK:
                    List<Brick> bricks = readBrickCsv(lines);
                    brickRepository.saveAll(bricks);
                    break;
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
    

    protected List<Brick> readBrickCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 4)
                .map(line ->{
                    return new Brick(line[0], line[1],Integer.parseInt(line[2]), line[3]);
                })
                .collect(Collectors.toList());
    }

    protected List<Color> readColorCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 4)
                .map(line ->{
                    return new Color(Integer.parseInt(line[0]),line[1],line[2], Boolean.parseBoolean(line[3]));
                })
                .collect(Collectors.toList());
    }

    protected List<Element> readElementCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new Element(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Inventory> readInventoryCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new Inventory(Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[2]);
                })
                .collect(Collectors.toList());
    }

    protected List<InventoryMinifig> readInventoryMinifigCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new InventoryMinifig(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }
    protected List<InventoryPart> readInventoryPartCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 5)
                .map(line ->{
                    return new InventoryPart(Integer.parseInt(line[0]),line[1],line[2], Integer.parseInt(line[3]), Boolean.parseBoolean(line[4]));
                })
                .collect(Collectors.toList());
    }

    protected List<InventorySet> readInventorySetCsv(List<String[]> lines) {
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new InventorySet(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Minifig> readMinifigCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new Minifig(line[0],line[1],Integer.parseInt(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<Part> readPartCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 4)
                .map(line ->{
                    return new Part(line[0], line[1],Integer.parseInt(line[2]), line[3]);
                })
                .collect(Collectors.toList());
    }

    protected List<PartCategory> readPartCategoryCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 2)
                .map(line ->{
                    return new PartCategory(Integer.parseInt(line[0]), line[1]);
                })
                .collect(Collectors.toList());
    }

    protected List<PartRelationship> readPartRelationshipCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new PartRelationship(line[0], line[1], line[2]);
                })
                .collect(Collectors.toList());
    }

    protected List<Set> readSetCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 5)
                .map(line ->{
                    return new Set(line[0], line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]));
                })
                .collect(Collectors.toList());
    }

    protected List<Theme> readThemeCsv(List<String[]> lines){
        return lines.stream()
                .filter(line -> line.length == 3)
                .map(line ->{
                    return new Theme(Integer.parseInt(line[0]),line[1], ImportUtils.parseIntegerOrNull(line[2]));
                })
                .collect(Collectors.toList());
    }

    protected List<String[]> readCsvLines(InputStream fileInput){
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInput));
        List<String[]> lines = br.lines()
                .map(line -> {
                    List<String> sl = ImportUtils.splitLineOnDelimiter(line, ',');
                    return sl.toArray(new String[sl.size()]);
                })
                .collect(Collectors.toList());
        lines.remove(0);
        return lines;
    }






}
