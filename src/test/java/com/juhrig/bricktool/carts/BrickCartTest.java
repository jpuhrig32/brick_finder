package com.juhrig.bricktool.carts;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickCartTest {
    @Test
    void testCompareCarts_exclusive() {
        BrickQuantity[] cart1Quantities = {
                new BrickQuantity(1, 4),
                new BrickQuantity(2, 3),
                new BrickQuantity(3, 5),
        };
        BrickQuantity[] cart2Quantities = {
                new BrickQuantity(4, 4),
                new BrickQuantity(5, 3),
                new BrickQuantity(6, 5),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 0.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<BrickQuantity> compBricks = compResult.getMissingPieces().getBricks();
        List<BrickQuantity> cart2Bricks = cart2.getBricks();

        assertEquals(cart2Bricks.size(), compBricks.size());
        compBricks.sort(Comparator.comparing(BrickQuantity::getId));
        cart2Bricks.sort(Comparator.comparing(BrickQuantity::getId));
        for(int i =0; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getId(), compBricks.get(i).getId());
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i).getQuantity());
        }
        assertTrue(compResult.getCommonPieces().getBricks().size() == 0);
    }

    @Test
    void testCompareCarts_equalCarts(){
        BrickQuantity[] cart1Quantities = {
                new BrickQuantity(1, 4),
                new BrickQuantity(2, 3),
                new BrickQuantity(3, 5),
        };
        BrickQuantity[] cart2Quantities = {
                new BrickQuantity(1, 4),
                new BrickQuantity(2, 3),
                new BrickQuantity(3, 5),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 1.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<BrickQuantity> compBricks = compResult.getCommonPieces().getBricks();
        List<BrickQuantity> cart2Bricks = cart2.getBricks();

        assertEquals(cart2Bricks.size(), compBricks.size());
        compBricks.sort(Comparator.comparing(BrickQuantity::getId));
        cart2Bricks.sort(Comparator.comparing(BrickQuantity::getId));
        for(int i =0; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getId(), compBricks.get(i).getId());
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i).getQuantity());
        }
        assertTrue(compResult.getMissingPieces().getBricks().size() == 0);
    }

    @Test
    void testCompareCarts_oneMatchExact(){
        BrickQuantity[] cart1Quantities = {
                new BrickQuantity(1, 4),
                new BrickQuantity(2, 3),
                new BrickQuantity(3, 5),
        };
        BrickQuantity[] cart2Quantities = {
                new BrickQuantity(3, 5),
                new BrickQuantity(5, 3),
                new BrickQuantity(6, 5),
        };
        BrickCart cart1 = new BrickCart(Arrays.asList(cart1Quantities));
        BrickCart cart2 = new BrickCart(Arrays.asList(cart2Quantities));

        BrickCartComparisonResult compResult = cart1.compareCarts(cart2);

        double expectedSimilarity = 5.0/13.0;

        assertEquals(expectedSimilarity, compResult.getSimilarity(), 0.001);
        List<BrickQuantity> compBricks = compResult.getMissingPieces().getBricks();
        List<BrickQuantity> cart2Bricks = cart2.getBricks();

        assertEquals(2, compBricks.size());
        compBricks.sort(Comparator.comparing(BrickQuantity::getId));
        cart2Bricks.sort(Comparator.comparing(BrickQuantity::getId));
        for(int i =1; i < cart2Bricks.size(); i++){
            assertEquals(cart2Bricks.get(i).getId(), compBricks.get(i-1).getId());
            assertEquals(cart2Bricks.get(i).getQuantity(), compBricks.get(i-1).getQuantity());
        }
        assertTrue(compResult.getCommonPieces().getBricks().size() == 1);
        assertEquals(compResult.getCommonPieces().getBricks().get(0).getId(), cart1Quantities[2].getId());
        assertEquals(compResult.getCommonPieces().getBricks().get(0).getQuantity(), cart1Quantities[2].getQuantity());
    }

}