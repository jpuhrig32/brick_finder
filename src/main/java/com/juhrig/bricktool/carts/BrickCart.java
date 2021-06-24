package com.juhrig.bricktool.carts;

import java.util.*;


public class BrickCart {

    List<BrickQuantity> bricks;
    Map<Integer, BrickQuantity> brickMap;

    public BrickCart(){
        bricks = new ArrayList<>();
        buildBrickMap();
    }

    public BrickCart(List<BrickQuantity> bricks){
        this.bricks = bricks;
        buildBrickMap();
    }


    public BrickCartComparisonResult compareCarts(BrickCart toCompare){
        sortCartContents();
        toCompare.sortCartContents();
        List<BrickQuantity> compList = toCompare.getBricks();

        int totalPieces = compList.stream().mapToInt(b -> b.getQuantity()).sum();
        int matchingPieces = 0;

        int compIndex = 0;
        int thisIndex = 0;
        boolean keepLooping = true;

        BrickCart missingPieceCart = new BrickCart();
        BrickCart matchingPieceCart = new BrickCart();


        while(keepLooping){
            if(bricks.get(thisIndex).getId() == compList.get(compIndex).getId()){
                int matchingQuantity = Math.min(bricks.get(thisIndex).getQuantity(),compList.get(compIndex).getQuantity());
                matchingPieceCart.addBrick(new BrickQuantity(bricks.get(thisIndex).getId(), matchingQuantity));
                matchingPieces += matchingQuantity;
                if(bricks.get(thisIndex).getQuantity() < compList.get(compIndex).getQuantity()){
                   missingPieceCart.addBrick(new BrickQuantity(bricks.get(thisIndex).getId(), compList.get(compIndex).getQuantity() - bricks.get(thisIndex).getQuantity()));
                }
                compIndex++;
                thisIndex++;
                keepLooping = compIndex == compList.size() || thisIndex == bricks.size()? false: true;

            }
            else if(bricks.get(thisIndex).getId() > compList.get(compIndex).getId())
            {
                missingPieceCart.addBrick(compList.get(compIndex));
                compIndex++;
                keepLooping = compIndex == compList.size() ? false: true;
            }
            else{
                thisIndex++;
                keepLooping = thisIndex == bricks.size() ? false: true;
            }

        }

        if(thisIndex == bricks.size() && compIndex < compList.size()){
            for(int i = compIndex; i < compList.size(); i++){
                missingPieceCart.addBrick(compList.get(i));
            }
        }

        return new BrickCartComparisonResult(((double)matchingPieces)/totalPieces, missingPieceCart, matchingPieceCart);

    }

    public void addBrick(BrickQuantity toAdd){
        if(brickMap.containsKey(toAdd.getId())){
            BrickQuantity foundQuantity = brickMap.get(toAdd.getId());
            int newQuantity = foundQuantity.getQuantity() + toAdd.getQuantity();
            foundQuantity.setQuantity(newQuantity);
        }
        else{
            bricks.add(toAdd);
            brickMap.put(toAdd.getId(), toAdd);
        }
    }

    public void addCart(BrickCart toAdd){
        toAdd.getBricks().stream().forEach(b ->
                addBrick(b));
    }

    public List<BrickQuantity> getBricks() {
        return bricks;
    }

    protected void sortCartContents(){
        bricks.sort(Comparator.comparing(BrickQuantity::getId));
    }

    private void buildBrickMap(){
        brickMap = new LinkedHashMap<>();
        bricks.stream().forEach(b -> {
            brickMap.put(b.getId(), b);
        });
    }
}
