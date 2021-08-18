package com.juhrig.bricktool.carts;

public class BrickCartComparisonResult {

    private final double similarity;
    private final BrickCart missingPieces;
    private final BrickCart commonPieces;

    public BrickCartComparisonResult(double similarity, BrickCart missingPieces, BrickCart commonPieces){
        this.similarity = similarity;
        this.missingPieces = missingPieces;
        this.commonPieces = commonPieces;
    }

    public double getSimilarity() {
        return similarity;
    }

    public BrickCart getMissingPieces() {
        return missingPieces;
    }

    public BrickCart getCommonPieces() {
        return commonPieces;
    }
}
