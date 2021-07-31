package com.juhrig.bricktool.dataimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImportUtils {
    private static Logger LOG = LoggerFactory.getLogger(ImportUtils.class);
    /*
    Basic CSV handling function, respects commas in quotes
     */
    public static List<String> splitLineOnDelimiter(String line, char delimiter){
        List<String> lineParts = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean inQuote = false;
        for(int i =0; i < line.length(); i++){
            char currentChar = line.charAt(i);
            if(currentChar == '"'){
                inQuote = !inQuote;
            }
            else if(currentChar == delimiter && !inQuote){
                lineParts.add(currentString.toString());
                currentString = new StringBuilder();
            }
            else{
                currentString.append(currentChar);
            }
        }
        lineParts.add(currentString.toString());

        return lineParts.stream()
                .map(lp -> lp.trim())
                .collect(Collectors.toList());
    }

    public static Integer parseIntegerOrNull(String toParse){
        try{
            return Integer.parseInt(toParse);
        }
        catch(NullPointerException | NumberFormatException e){
            LOG.debug(String.format("Tried to parse a null or invalid string to int: %s", toParse), e);
            return null;
        }
    }
}
