package worldgen;

public enum Biome {

    PLAINS,
    FOREST,
    DESERT,
    SAVANNA,
    JUNGLE,
    TUNDRA,
    TAIGA,
    SNOWY,
    SWAMP;


    public static Biome getBiome(int heat, int humidity){
        return heat>0?humidity>0? Biome.JUNGLE: humidity == 0 ? Biome.SAVANNA: Biome.DESERT:heat==0?humidity>0?Biome.SWAMP:humidity==0?Biome.FOREST:Biome.PLAINS:humidity>0?Biome.TAIGA:humidity==0?Biome.TUNDRA:Biome.SNOWY;


    }

}
