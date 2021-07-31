package com.juhrig.bricktool.dataimport;

import com.juhrig.bricktool.dto.*;
import org.springframework.context.annotation.Import;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RebrickableDataImporter {



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
