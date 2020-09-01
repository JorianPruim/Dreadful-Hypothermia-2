public class World{
	private Tile[][] tiles;
	private List<Building> buildings;
	private List<Underground> undergrounds;
	private List<Entity> entities;
	
	private World(){}
	
	private World(Tile[][] tiles, List<Building> buildings, List<Underground> undergrounds, List<Entity> entities){
		this.tiles = tiles;
		this.buildings = buildings;
		this.undergrounds = undergrounds;
		this.entities = entities;
	}
	
	public static World createNew(){
		return this.createNew((new java.lang.Random).nextInt());
	}
	public static World createNew(int seed){
		//TODO
		//WORLDGEN
	}
	public static World load(){
		//TODO
	}
	
	public Tile getTile(int x, int z){
		return this.tiles[x][z];
	}
	
	public Building getBuilding(int x, int z){
		for(Building b : buildings){
			if(b.getPos().getX() == x && b.getPos().getZ() == z)return b;
		}
	}
	
	public List<Underground> getUndergrounds(int x, int z){
		List<Underground> result;
		for(Underground u : undergrounds){
			if(u.getPos().getX() == x && u.getPos().getZ() == z){
				result.add(u);
			}
		}
		return result;
	}
