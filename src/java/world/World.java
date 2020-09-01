package world;
import java.util.ArrayList;
import java.util.List;

public class World {
	private Tile[][] tiles;
	private List<Building> buildings;
	private List<Underground> undergrounds;
	private List<Entity> entities;
	private static World EMPTY = new World();
	
	private World(){}
	
	private World(Tile[][] tiles, List<Building> buildings, List<Underground> undergrounds, List<Entity> entities){
		this.tiles = tiles;
		this.buildings = buildings;
		this.undergrounds = undergrounds;
		this.entities = entities;
	}
	
	public static World createNew(){
		return createNew((new java.util.Random()).nextInt());
	}
	public static World createNew(int seed){
		//TODO
		//WORLDGEN
		return World.EMPTY;
	}
	public static World load(){
		//TODO
		return World.EMPTY;
	}
	
	public Tile getTile(int x, int z){
		return this.tiles[x][z];
	}
	
	public Building getBuilding(int x, int z){
		for(Building b : buildings){
			if(b.getPos().getX() == x && b.getPos().getZ() == z)return b;
		}
		return null;
	}
	
	public List<Underground> getUndergrounds(int x, int z){
		List<Underground> result = new ArrayList<>();
		for(Underground u : undergrounds){
			if(u.getPos().getX() == x && u.getPos().getZ() == z){
				result.add(u);
			}
		}
		return result;
	}

	public Entity getEntity(int x, int z){
		for(Entity e : entities){
			if(e.getPos().getX() == x && e.getPos().getZ() == z)return e;
		}
		return null;
	}

}
