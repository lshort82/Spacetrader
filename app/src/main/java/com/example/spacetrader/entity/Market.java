package com.example.spacetrader.entity;

import android.widget.Toast;

import java.util.List;

public class Market {
    List<Item> inventory;
    List<Integer> quantity;
    List<Integer> price;
    int TechLevelofLocation;
    public Market(Planet planet){
        //to be implemented
    }

    public Market(List<Item> inventory, List<Integer> quantity){
        while(inventory.size() > quantity.size()){
            quantity.add(0);
        }
        this.inventory=inventory;
        this.quantity = quantity;
        for(int i =0; i< inventory.size(); i++){
            price.add(getPrice(i));
        }
    }
    public void resetPrices(){
        for(int i =0; i< inventory.size(); i++){
            price.set(i,getPrice(i));
        }
    }
    public int getPrice(int index) {
        if(index >= inventory.size()) {
            return 99999;
        }
        int base = inventory.get(index).getPrice();
        int var = Math.random() >.5 ? 1 : -1;
        var = (int)(Math.random()*inventory.get(index).getVariance() + 1) * var;
        var += inventory.get(index).getIncPerTech() * (TechLevelofLocation);
        return base + var;
    }

    public int salePrice(Item item) {
        int base = item.getPrice();
        int var = Math.random() >.5 ? 1 : -1;
        var = (int)(Math.random()*item.getVariance() + 1) * var;
        var += item.getIncPerTech() * (TechLevelofLocation);
        return base + var;
    }

    public String makePurchase(Player player, int index, int quantity){
        if(player.spaceLeft() < quantity) {
            return "Not enough space in inventory";
        }
        if(player.getCredits() < price.get(index) * quantity){
            return "Not Enough Credits";
        }
        player.setCredits(player.getCredits()- (price.get(index) * quantity));
        player.addItem(inventory.get(index), quantity);
        this.quantity.set(index,this.quantity.get(index)- quantity);
        return "Purchase made!";
    }

    public String makeSale(Player player, Item item, int quantity,int price) {
        if(player.getItemQuantity(item) < quantity) {
            return "Not enough of " + item.getName()+ " to sell!";
        }
        else {
            player.removeItem(item,quantity);
            player.setCredits(price*quantity + player.getCredits());
            return "Sale made!";
        }
    }
}
