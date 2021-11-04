package com.juhrig.bricktool.carts;

import com.juhrig.bricktool.datasource.dto.InventoryPart;
import com.juhrig.bricktool.datasource.dto.InventoryPartImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickCartTest {
    @Test
    void testCompareCarts_exclusive() {
        InventoryPart[] cart1Quantities = {
                new InventoryPartImpl(1, "1", 1, 4, false),
                new InventoryPartImpl(2, "2", 1,3, false),
                new InventoryPartImpl(3, "3",  1,5, false),
        };
        InventoryPart[] cart2Quantities = {
                new InventoryPartImpl(4, "4", 1, 4, false),
                new InventoryPartImpl(5, "5", 1,3, false),
                new InventoryPartImpl(6, "6",  1,5, false),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 0.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<InventoryPart> compBricks = compResult.getCommonPieces().getBricks();
        List<InventoryPart> cart2Bricks = cart2.getBricks();

        assertEquals(0, compBricks.size());
        compBricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        cart2Bricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        for(int i =0; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i).getQuantity());
        }
        assertTrue(compResult.getCommonPieces().getBricks().size() == 0);
    }

    @Test
    void testCompareCarts_equalCarts(){
        //int inventoryId, String partNumber, String colorId, int quantity, boolean isSpare
        InventoryPartImpl[] cart1Quantities = {
                new InventoryPartImpl(1, "1", 1, 4, false),
                new InventoryPartImpl(2, "2", 1,3, false),
                new InventoryPartImpl(3, "3",  1,5, false),
        };
        InventoryPartImpl[] cart2Quantities = {
                new InventoryPartImpl(1, "1", 1, 4, false),
                new InventoryPartImpl(2, "2", 1,3, false),
                new InventoryPartImpl(3, "3",  1,5, false),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 1.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<InventoryPart> compBricks = compResult.getCommonPieces().getBricks();
        List<InventoryPart> cart2Bricks = cart2.getBricks();

        assertEquals(cart2Bricks.size(), compBricks.size());
        compBricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        cart2Bricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        for(int i =0; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getInventoryId(), compBricks.get(i).getInventoryId());
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i).getQuantity());
        }
        assertTrue(compResult.getMissingPieces().getBricks().size() == 0);
    }

    @Test
    void testCompareCarts_oneMatchExact(){
        InventoryPartImpl[] cart1Quantities = {
                new InventoryPartImpl(1, "1", 1, 4, false),
                new InventoryPartImpl(2, "2", 1,3, false),
                new InventoryPartImpl(3, "3",  1,5, false),
        };
        InventoryPartImpl[] cart2Quantities = {
                new InventoryPartImpl(1, "1", 1, 5, false),
                new InventoryPartImpl(2, "2", 1,3, false),
                new InventoryPartImpl(3, "3",  1,5, false),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 5.0/13.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<InventoryPart> compBricks = compResult.getMissingPieces().getBricks();
        List<InventoryPart> cart2Bricks = cart2.getBricks();

        assertEquals(2, compBricks.size());
        compBricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        cart2Bricks.sort(Comparator.comparing(InventoryPart::getInventoryId));
        for(int i =1; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getInventoryId(), compBricks.get(i-1).getInventoryId());
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i-1).getQuantity());
        }
        assertTrue(compResult.getCommonPieces().getBricks().size() == 1);
        assertEquals(compResult.getCommonPieces().getBricks().get(0).getInventoryId(), cart1Quantities[2].getInventoryId());
        assertEquals(compResult.getCommonPieces().getBricks().get(0).getQuantity(), cart1Quantities[2].getQuantity());
    }

}