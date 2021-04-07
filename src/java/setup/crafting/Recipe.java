package setup.crafting;

import objects.buildings.Station;
import objects.items.Item;
import setup.player.Inventory;
import setup.player.Player;
import setup.register.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class Recipe extends RegistryObject {
    public Station station;
    public Knowledge pre;
    public List<Item> in;
    public Item out;

    public Recipe(List<Item> in, Item out, Knowledge pre, Station station){
        this.in = in;
        this.out = out;
        this.pre = pre;
        this.station = station;
    }
    public Recipe(List<Item> in, Item out){
        this(in, out, Knowledge.ROOT, null);
    }
    public Recipe(Item out, Item... in){
        this(Arrays.asList(in), out);
    }


    public boolean isCraftable(Player p, List<Station> nearStations){
        Inventory inv = p.getInventory();
        nearStations.add(null);
        System.out.println(nearStations);
        System.out.println(p.k);
        System.out.println(inv);
        return nearStations.contains(station) && p.knows(pre) && inv.containsAll(in);
    }

    public void craft(Inventory inv){
        for(Item i : in){
            inv.removeFromInv(i);
        }
        inv.addToInv(out);
    }


}
