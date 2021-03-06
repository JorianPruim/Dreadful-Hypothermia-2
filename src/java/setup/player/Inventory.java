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
    Item selected;
    int selectedIndex = -1;

    public Inventory(){
        this(Double.MAX_VALUE,Double.MAX_VALUE);
    }

    public Inventory(double maxSize, double maxWeight){
        this.maxSize = maxSize;
        this.maxWeight = maxWeight;
        this.contents = new ArrayList<>();
    }



    public boolean addToInv(Item item){

        Item total = getItemTotal();
        if(total.getWeight()+item.getWeight() > maxWeight || total.getSize()+item.getSize() > maxSize){
            return false;
        }else{
            contents.add(item);
            return true;
        }
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

    public boolean removeFromInv(Item item){
        this.selectedIndex = -1;
        this.selected = null;
        return contents.remove(item);
    }

    public int removeFromInv(Item item, int quantity){
        while(quantity!=0&&removeFromInv(item)){
            quantity--;
        }
        return quantity;
    }

    public void select(int i){
        this.selected = contents.get(i);
        this.selectedIndex = i;
    }

    public Item getSelected() {
        return selected;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private Item getItemTotal() {
        return contents.stream().reduce(new Item(),(a, b)->new Item(a.getSize()+b.getSize(),a.getWeight()+b.getWeight()));
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

    public boolean containsAll(List<Item> c){
        List<Item> copy = new ArrayList<>(this.contents);
        for(Item i : c){
            if(!copy.contains(i)) {
                return false;
            }else{
                copy.remove(i);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return contents.toString();
    }
}
