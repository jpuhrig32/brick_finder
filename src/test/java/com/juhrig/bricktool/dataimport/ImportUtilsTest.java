package com.juhrig.bricktool.dataimport;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportUtilsTest {
    private final char DELIMITER = ',';
    @Test
    void safeSplitLine_no_splits() {
        String testString = "This is a string";
        List<String> expectedValues = List.of("This is a string");
        List<String> actualValues = ImportUtils.splitLineOnDelimiter(testString, DELIMITER);
        assertNotNull(actualValues);
        compareLists(expectedValues, actualValues);
    }
    @Test
    void safeSplitLine_one_split() {
        String testString = "This is a string, This is also a string";
        List<String> expectedValues = List.of("This is a string", "This is also a string");
        List<String> actualValues = ImportUtils.splitLineOnDelimiter(testString, DELIMITER);
        assertNotNull(actualValues);
        compareLists(expectedValues, actualValues);
    }

    @Test
    void safeSplitLine_two_split() {
        String testString = "This is a string, This is also a string, Aren't you tired yet?";
        List<String> expectedValues = List.of("This is a string", "This is also a string", "Aren't you tired yet?");
        List<String> actualValues = ImportUtils.splitLineOnDelimiter(testString, DELIMITER);
        assertNotNull(actualValues);
        compareLists(expectedValues, actualValues);
    }

    @Test
    void safeSplitLine_withQuotes() {
        String testString = "This is a string, \"This is also a string\", Aren't you tired yet?";
        List<String> expectedValues = List.of("This is a string", "This is also a string", "Aren't you tired yet?");
        List<String> actualValues = ImportUtils.splitLineOnDelimiter(testString, DELIMITER);
        assertNotNull(actualValues);
        compareLists(expectedValues, actualValues);
    }
    @Test
    void safeSplitLine_withQuotesAndDelimiter() {
        String testString = "This is a string, \"This is also, a string\", Aren't you tired yet?";
        List<String> expectedValues = List.of("This is a string", "This is also, a string", "Aren't you tired yet?");
        List<String> actualValues = ImportUtils.splitLineOnDelimiter(testString, DELIMITER);
        assertNotNull(actualValues);
        compareLists(expectedValues, actualValues);
    }

    private void compareLists(List<String> expectedList, List<String> actualList){
        assertEquals(expectedList.size(),actualList.size());
        for(int i = 0; i < expectedList.size(); i++){
           assertTrue(expectedList.get(i).equals(actualList.get(i)), String.format("Expected string %s did not match actual string %s", expectedList.get(i), actualList.get(i)));
        }
    }
}