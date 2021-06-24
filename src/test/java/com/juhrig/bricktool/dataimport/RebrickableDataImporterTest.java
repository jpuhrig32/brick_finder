package com.juhrig.bricktool.dataimport;

import com.juhrig.bricktool.dto.Color;
import com.juhrig.bricktool.dto.Element;
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

        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readElementCsv() {
        int expectedObjectCount = 5;
        Color[] expectedObjectsArray = new Element[]{
                new Element("4275423", "[Unknown]", "0033B2", false),
                new Element(0, "Black", "05131D", false),
                new Element(1, "Blue", "0055BF", false),
                new Element(2, "Green", "237841", false),
                new Element(3, "Dark Turquoise", "008F9B", false)
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

        compareLists(expectedObjects, testObjects);
    }

    @Test
    void readInventoryCsv() {
    }

    @Test
    void readInventoryMinifigCsv() {
    }

    @Test
    void readInventorySetCsv() {
    }

    @Test
    void readMinifigCsv() {
    }

    @Test
    void readPartCsv() {
    }

    @Test
    void readPartCategoryCsv() {
    }

    @Test
    void readPartRelationshipCsv() {
    }

    @Test
    void readSetCsv() {
    }

    @Test
    void readThemeCsv() {
    }

    <T> boolean compareLists(List<T> expected, List<T> actual){
        assertEquals(expected.size(),actual.size(), "Expected list size does not match actual list size");
        for(int i =0; i < expected.size(); i++){
            assertEquals(expected.get(i), actual.get(i));
        }
        return true;
    }
}