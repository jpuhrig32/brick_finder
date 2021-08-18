package com.juhrig.bricktool.dataimport;

import com.juhrig.bricktool.dto.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RebrickableDataImporterTest {

    @Test
    void readColorCsv() {
        int expectedObjectCount = 5;
        Color[] expectedObjectsArray = new Color[]{
            new Color(-1, "[Unknown]", "0033B2", false),
            new Color(0, "Black", "05131D", false),
            new Color(1, "Blue", "0055BF", false),
            new Color(2, "Green", "237841", false),
            new Color(3, "Dark Turquoise", "008F9B", false)
        };
        List<Color> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "id,name,rgb,is_trans\n" +
                "-1,[Unknown],0033B2,f\n" +
                "0,Black,05131D,f\n" +
                "1,Blue,0055BF,f\n" +
                "2,Green,237841,f\n" +
                "3,Dark Turquoise,008F9B,f";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);


        List<Color> testObjects = rdi.readColorCsv(lines);
        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readElementCsv() {
        int expectedObjectCount = 5;
        Element[] expectedObjectsArray = new Element[]{
                new Element(4275423, "53657", 1004),
                new Element(6194308, "92926", 71),
                new Element(6229123, "26561", 4),
                new Element(4241969, "51035", 1004),
                new Element(4257250, "50899pat0001", -1)
        };
        List<Element> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "element_id,part_num,color_id\n" +
                "4275423,53657,1004\n" +
                "6194308,92926,71\n" +
                "6229123,26561,4\n" +
                "4241969,51035,1004\n" +
                "4257250,50899pat0001,-1";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);


        List<Element> testObjects = rdi.readElementCsv(lines);
        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readInventoryCsv() {
        int expectedObjectCount = 5;
        Inventory[] expectedObjectsArray = new Inventory[]{
                new Inventory(1, 1, "7922-1"),
                new Inventory(3, 1, "3931-1"),
                new Inventory(4, 1, "6942-1"),
                new Inventory(15, 1, "5158-1"),
                new Inventory(16, 1, "903-1"),
        };
        List<Inventory> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "id,version,set_num\n" +
                "1,1,7922-1\n" +
                "3,1,3931-1\n" +
                "4,1,6942-1\n" +
                "15,1,5158-1\n" +
                "16,1,903-1";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);


        List<Inventory> testObjects = rdi.readInventoryCsv(lines);
        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readInventoryMinifigCsv() {
        int expectedObjectCount = 5;
        InventoryMinifig[] expectedObjectsArray = new InventoryMinifig[]{
                new InventoryMinifig(3, "fig-001549", 1),
                new InventoryMinifig(4, "fig-000764", 1),
                new InventoryMinifig(19, "fig-000555", 1),
                new InventoryMinifig(25, "fig-000574", 1),
                new InventoryMinifig(26, "fig-000842", 1),
        };
        List<InventoryMinifig> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "inventory_id,fig_num,quantity\n" +
                "3,fig-001549,1\n" +
                "4,fig-000764,1\n" +
                "19,fig-000555,1\n" +
                "25,fig-000574,1\n" +
                "26,fig-000842,1";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);


        List<InventoryMinifig> testObjects = rdi.readInventoryMinifigCsv(lines);
        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readInventoryPartCsv(){
        int expectedObjectCount = 5;
        InventoryPart[] expectedObjectsArray = new InventoryPart[]{
                new InventoryPart(1, "48379c01", "72", 1, false),
                new InventoryPart(1, "48395", "7", 1, false),
                new InventoryPart(1, "stickerupn0077", "9999", 1, false),
                new InventoryPart(1, "upn0342", "0", 1, false),
                new InventoryPart(1, "upn0350", "25", 1, false),
        };
        List<InventoryPart> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "inventory_id,part_num,color_id,quantity,is_spare\n" +
                "1,48379c01,72,1,f\n" +
                "1,48395,7,1,f\n" +
                "1,stickerupn0077,9999,1,f\n" +
                "1,upn0342,0,1,f\n" +
                "1,upn0350,25,1,f";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<InventoryPart> testObjects = rdi.readInventoryPartCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readInventorySetCsv() {
        int expectedObjectCount = 5;
        InventorySet[] expectedObjectsArray = new InventorySet[]{
                new InventorySet(35, "75911-1", 1),
                new InventorySet(35, "75912-1", 1),
                new InventorySet(39, "75048-1", 1),
                new InventorySet(39, "75053-1", 1),
                new InventorySet(50, "4515-1", 1),
        };
        List<InventorySet> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "inventory_id,set_num,quantity\n" +
                "35,75911-1,1\n" +
                "35,75912-1,1\n" +
                "39,75048-1,1\n" +
                "39,75053-1,1\n" +
                "50,4515-1,1";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);


        List<InventorySet> testObjects = rdi.readInventorySetCsv(lines);
        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readMinifigCsv() {
        int expectedObjectCount = 5;
        Minifig[] expectedObjectsArray = new Minifig[]{
                new Minifig("fig-000001", "Toy Store Employee", 4),
                new Minifig("fig-000002", "Customer Kid", 4),
                new Minifig("fig-000003", "Assassin Droid, White", 8),
                new Minifig("fig-000004", "Basic Figure", 4),
                new Minifig("fig-000005", "Captain America with Short Legs", 3),
        };
        List<Minifig> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "fig_num,name,num_parts\n" +
                "fig-000001,Toy Store Employee,4\n" +
                "fig-000002,Customer Kid,4\n" +
                "fig-000003,\"Assassin Droid, White\",8\n" +
                "fig-000004,Basic Figure,4\n" +
                "fig-000005,Captain America with Short Legs,3";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<Minifig> testObjects = rdi.readMinifigCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readPartCsv() {
        int expectedObjectCount = 5;
        Part[] expectedObjectsArray = new Part[]{
                new Part("003381", "Sticker Sheet for Set 663-1", 58, "Plastic"),
                new Part("003383", "Sticker Sheet for Sets 618-1, 628-2", 58, "Plastic"),
                new Part("003402", "Sticker Sheet for Sets 310-3, 311-1, 312-3", 58, "Plastic"),
                new Part("003429", "Sticker Sheet for Set 1550-1", 58, "Plastic"),
                new Part("003432", "Sticker Sheet for Sets 357-1, 355-1, 940-1", 58, "Plastic"),
        };
        List<Part> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "part_num,name,part_cat_id,part_material\n" +
                "003381,Sticker Sheet for Set 663-1,58,Plastic\n" +
                "003383,\"Sticker Sheet for Sets 618-1, 628-2\",58,Plastic\n" +
                "003402,\"Sticker Sheet for Sets 310-3, 311-1, 312-3\",58,Plastic\n" +
                "003429,Sticker Sheet for Set 1550-1,58,Plastic\n" +
                "003432,\"Sticker Sheet for Sets 357-1, 355-1, 940-1\",58,Plastic";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<Part> testObjects = rdi.readPartCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);

    }

    @Test
    void readPartCategoryCsv() {
        int expectedObjectCount = 5;
        PartCategory[] expectedObjectsArray = new PartCategory[]{
                new PartCategory(1, "Baseplates"),
                new PartCategory(3, "Bricks Sloped"),
                new PartCategory(4, "Duplo, Quatro and Primo"),
                new PartCategory(5, "Bricks Special"),
                new PartCategory(6, "Bricks Wedged"),
               
        };
        List<PartCategory> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "id,name\n" +
                "1,Baseplates\n" +
                "3,Bricks Sloped\n" +
                "4,\"Duplo, Quatro and Primo\"\n" +
                "5,Bricks Special\n" +
                "6,Bricks Wedged";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<PartCategory> testObjects = rdi.readPartCategoryCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);

    }

    @Test
    void readPartRelationshipCsv() {
        int expectedObjectCount = 5;
        PartRelationship[] expectedObjectsArray = new PartRelationship[]{
                new PartRelationship("M", "3192a","3192"),
                new PartRelationship("A", "50990b","50990a"),
                new PartRelationship("P", "27382pr0005","27382"),
                new PartRelationship("P", "3298pr0033","3298"),
                new PartRelationship("R", "4109154pr0002","4109154pr0001"),

        };
        List<PartRelationship> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "rel_type,child_part_num,parent_part_num\n" +
                "M,3192a,3192\n" +
                "A,50990b,50990a\n" +
                "P,27382pr0005,27382\n" +
                "P,3298pr0033,3298\n" +
                "R,4109154pr0002,4109154pr0001";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<PartRelationship> testObjects = rdi.readPartRelationshipCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readSetCsv() {
        int expectedObjectCount = 5;
        Set[] expectedObjectsArray = new Set[]{
                new Set("001-1", "Gears",1965, 1, 43),
                new Set("0011-2", "Town Mini-Figures",1979, 84, 12),
                new Set("0011-3", "Castle 2 for 1 Bonus Offer",1987, 199, 0),
                new Set("0012-1", "Space Mini-Figures",1979, 143, 12),
                new Set("0013-1", "Space Mini-Figures",1979, 143, 12),
                
        };
        List<Set> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "set_num,name,year,theme_id,num_parts\n" +
                "001-1,Gears,1965,1,43\n" +
                "0011-2,Town Mini-Figures,1979,84,12\n" +
                "0011-3,Castle 2 for 1 Bonus Offer,1987,199,0\n" +
                "0012-1,Space Mini-Figures,1979,143,12\n" +
                "0013-1,Space Mini-Figures,1979,143,12";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<Set> testObjects = rdi.readSetCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readThemeCsv() {
        int expectedObjectCount = 5;
        Theme[] expectedObjectsArray = new Theme[]{
                new Theme(1, "Technic", null),
                new Theme(2, "Arctic Technic", 1),
                new Theme(3, "Competition", 1),
                new Theme(4, "Expert Builder", 1),
                new Theme(5, "Model", 1),
               

        };
        List<Theme> expectedObjects = Arrays.asList(expectedObjectsArray);

        String exampleCsv = "id,name,parent_id\n" +
                "1,Technic,\n" +
                "2,Arctic Technic,1\n" +
                "3,Competition,1\n" +
                "4,Expert Builder,1\n" +
                "5,Model,1";

        InputStream testStream = new ByteArrayInputStream(exampleCsv.getBytes(StandardCharsets.UTF_8));

        RebrickableDataImporter rdi = new RebrickableDataImporter();
        List<String[]> lines = rdi.readCsvLines(testStream);
        List<Theme> testObjects = rdi.readThemeCsv(lines);

        assertEquals(expectedObjectCount, testObjects.size());
        compareLists(expectedObjects, testObjects);
    }

    <T> boolean compareLists(List<T> expected, List<T> actual){
        assertEquals(expected.size(),actual.size(), "Expected list size does not match actual list size");
        for(int i =0; i < expected.size(); i++){
            assertEquals(expected.get(i), actual.get(i), String.format("Expected [%s] found [%s]", expected.get(i).toString(), actual.get(i).toString()));
        }
        return true;
    }


}