package setup.player;

import setup.world.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Inventory {

    double maxWeight;
    double maxSize;
    List<Item> contents;

    public Inventory(){
        this.maxSize = 10;
        this.maxWeight = 10;
        this.contents = new ArrayList<>();
    }

    public synchronized boolean addToInv(Item item){
//        final double[] weight = {0};
//        final double[] size = {0};
        Item total = contents.stream().reduce(new Item(),(a,b)->new Item(a.getSize()+b.getSize(),a.getWeight()+b.getWeight()));
//        contents.forEach(i->{
//            weight[0] += i.getWeight();
//            size[0] += i.getSize();
//        });
        if(total.getWeight()+item.getWeight() > maxWeight || total.getSize()+item.getSize() > maxSize){
            return false;
        }else{
            contents.add(item);
            return true;
        }
    }

    public synchronized Queue<Item> addToInv(Queue<Item> items){
        while(!items.isEmpty() && addToInv(items.peek())){
            items.remove();
        }
        return items;
    }
}
