package com.juhrig.bricktool.carts;

import com.juhrig.bricktool.dto.Brick;
import com.juhrig.bricktool.dto.InventoryPart;

import javax.persistence.Transient;
import java.util.*;


public class BrickCart {

    @Transient
    protected List<InventoryPart> bricks;
    protected Map<String, InventoryPart> brickMap;

    public BrickCart(){
        bricks = new ArrayList<InventoryPart>();
        buildBrickMap();
    }

    public BrickCart(List<InventoryPart> bricks){
        this.bricks = bricks;
        buildBrickMap();
    }

    public BrickCartComparisonResult compareCarts(BrickCart toCompare){
        sortCartContents();
        toCompare.sortCartContents();
        List<InventoryPart> compList = toCompare.getBricks();

        int totalPieces = compList.stream().mapToInt(b -> b.getQuantity()).sum();
        int matchingPieces = 0;

        int compIndex = 0;
        int thisIndex = 0;
        boolean keepLooping = true;

        BrickCart missingPieceCart = new BrickCart();
        BrickCart matchingPieceCart = new BrickCart();


        while(keepLooping){
            if(bricks.get(thisIndex).isSamePart(compList.get(compIndex))){
                int matchingQuantity = Math.min(bricks.get(thisIndex).getQuantity(),compList.get(compIndex).getQuantity());
                matchingPieceCart.addBrick(new InventoryPart(bricks.get(thisIndex), matchingQuantity));
                matchingPieces += matchingQuantity;
                if(bricks.get(thisIndex).getQuantity() < compList.get(compIndex).getQuantity()){
                   missingPieceCart.addBrick(new InventoryPart(bricks.get(thisIndex), compList.get(compIndex).getQuantity() - bricks.get(thisIndex).getQuantity()));
                }
                compIndex++;
                thisIndex++;
                keepLooping = compIndex != compList.size() && thisIndex != bricks.size();

            }
            else if(bricks.get(thisIndex).compareTo(compList.get(compIndex)) <0)
            {
                missingPieceCart.addBrick(compList.get(compIndex));
                compIndex++;
                keepLooping = compIndex != compList.size();
            }
            else{
                thisIndex++;
                keepLooping = thisIndex != bricks.size();
            }

        }

        if(thisIndex == bricks.size() && compIndex < compList.size()){
            for(int i = compIndex; i < compList.size(); i++){
                missingPieceCart.addBrick(compList.get(i));
            }
        }

        return new BrickCartComparisonResult(((double)matchingPieces)/totalPieces, missingPieceCart, matchingPieceCart);

    }

    public void addBrick(InventoryPart toAdd){
        if(brickMap.containsKey(toAdd.getPartNumber())){
            InventoryPart foundQuantity = brickMap.get(toAdd.getPartNumber());
            int newQuantity = foundQuantity.getQuantity() + toAdd.getQuantity();
            foundQuantity.setQuantity(newQuantity);
        }
        else{
            bricks.add(toAdd);
            brickMap.put(toAdd.getPartNumber(), toAdd);
        }
        buildBrickMap();
    }

    public void addCart(BrickCart toAdd){
        toAdd.getBricks().stream().forEach(b ->
                addBrick(b));
        buildBrickMap();
    }

    public List<InventoryPart> getBricks() {
        return bricks;
    }


    public void sortCartContents(){
       Collections.sort(bricks);
    }

    private void buildBrickMap() {
        if (bricks != null) {
            brickMap = new LinkedHashMap<String, InventoryPart>();
            bricks.stream().forEach(b -> {
                brickMap.put(b.getPartNumber(), b);
            });
        }
    }
}
