package setup.player;

import objects.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Inventory {

    double maxWeight;
    double maxSize;
    List<Item> contents;

    public Inventory(){
        this(10,10);
    }

    public Inventory(double maxSize, double maxWeight){
        this.maxSize = maxSize;
        this.maxWeight = maxWeight;
        this.contents = new ArrayList<>();
    }

    public boolean addToInv(Item item){

        Item total = getItemTotal();
        if(total.getWeight()+item.getWeight() > maxWeight || total.getSize()+item.getSize() > maxSize){
            System.out.println(false);
            return false;
        }else{
            contents.add(item);
            return true;
        }
    }

    private Item getItemTotal() {
        Item total = contents.stream().reduce(new Item(),(a,b)->new Item(a.getSize()+b.getSize(),a.getWeight()+b.getWeight()));
        return total;
    }

    public int addToInv(Item item, int quantity){
        while(quantity!=0 && addToInv(item)){
            quantity--;
        }
        return quantity;
    }

    public List<Item> addToInv(List<Item> items){
        List<Item> residue = items.subList(0,0);
        for(Item item : items){
            if(!addToInv(item)){
                residue.add(item);
            }
        }
        return residue;
    }

    public int getLength(){
        return contents.size();
    }

    public Item getByIndex(int i){
        return contents.get(i);
    }

    public double getWeight(){
        return Math.round(getItemTotal().getWeight()*1e5)/1e5;
    }

    public double getOccupiedSpace(){
        return Math.round(getItemTotal().getSize()*1e5)/1e5;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getMaxSize() {
        return maxSize;
    }
}
