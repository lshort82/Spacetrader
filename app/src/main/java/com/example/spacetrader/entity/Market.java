package com.example.spacetrader.entity;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Market implements Serializable {
    List<Item> inventory;
    List<Integer> quantity;
    List<Integer> price;
    int techLevelofLocation;
    public Market(Planet planet){ // planet will be used for further implementation eventually
        this.inventory = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.price = new ArrayList<>();
        this.techLevelofLocation = planet.getTechnologyLevel();
        for (Item e : Item.values()){
            if(e.getMinTechProd() < this.techLevelofLocation) {
                this.inventory.add(e);
                this.quantity.add((int)(Math.random() * 10) + 1);
            }
        }
        for(int i =price.size(); i< inventory.size(); i++){
            price.add(getPrice(i));
        }
    }
    public Market(List<Item> inventory, List<Integer> quantity, List<Integer> price){
        this.inventory =inventory;
        this.quantity = quantity;
        this.price=price;
        for(int i =price.size(); i< inventory.size(); i++){
            price.add(getPrice(i));
        }
        techLevelofLocation = 0;
    }
    public Market(List<Item> inventory, List<Integer> quantity){
        this.inventory=inventory;
        this.quantity = quantity;
        price = new ArrayList<Integer>();
        for(int i =0; i< inventory.size(); i++){
            price.add(getPrice(i));
        }
        techLevelofLocation = 0;
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
        int var = -1;
        if(Math.random() >.5) {
            var = 1;
        }
        var = (int)(Math.random()*inventory.get(index).getVariance() + 1) * var;
        var += inventory.get(index).getIncPerTech() * (techLevelofLocation);
        return base + var;
    }

    public int salePrice(Item item) {
        int base = item.getPrice();
        int var = Math.random() >.5 ? 1 : -1;
        var = (int)(Math.random()*item.getVariance() + 1) * var;
        var += item.getIncPerTech() * (techLevelofLocation);
        return base + var;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public List<Integer> getPrice() {
        return price;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String makePurchase(Player player, Item item, int quantity){
        int index = inventory.indexOf(item);
        if(index == -1) {
            return "item not available";
        }
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

            int index = player.removeItem(item,quantity);
            if(player.getItemQuantity(item)==0) {
                this.price.remove(index);
            }
            player.setCredits(price*quantity + player.getCredits());
            return "Sale made!";
        }
    }

    public int getPrice(Item item) {
        if(inventory.indexOf(item) < 0) {
            return 99999;
        }
        return price.get(inventory.indexOf(item));
    }
}
