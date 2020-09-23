import java.util.*;
public class HelloWorld{

     public static void heatMap(int size, int maxDropSize, int dropletDensity){
        int[][] map = new int[size][size];
        int[][] humidityMap = new int[size][size];
        String[][] totalMap = new String[size][size];
        for(int i = 0; i < dropletDensity; i++){
            map = setValuePlus(map, maxDropSize, size, maxDropSize);
            map = setValueMin(map, maxDropSize, size, maxDropSize);
        } 
        for(int i = 0; i < size-1; i++){
            for (int j = 0; j < size-1; j++){
                if(map[i][j] != map[i+1][j] && Math.abs(map[i][j]) - Math.abs(map[i+1][j]) == 0 || map[i][j] != map[Math.abs(i-1)][j] && Math.abs(map[i][j]) - Math.abs(map[Math.abs(i-1)][j]) == 0 || map[i][j] != map[i][j+1] && Math.abs(map[i][j]) - Math.abs(map[i][j+1]) == 0 || map[i][j] != map[i][Math.abs(j-1)] && Math.abs(map[i][j]) - Math.abs(map[i][Math.abs(j-1)]) == 0){
                    map[i][j] = 0;
                }
            } 
        } 
        
        System.out.println("\n");
        
        for(int i = 0; i < dropletDensity; i++){
            humidityMap = setValuePlus(humidityMap, maxDropSize, size, maxDropSize);
            humidityMap = setValueMin(humidityMap, maxDropSize, size, maxDropSize);
        }
        
        for(int i = 0; i < size-1; i++){
            for (int j = 0; j < size-1; j++){
                if(humidityMap[i][j] != humidityMap[i+1][j] && Math.abs(humidityMap[i][j]) - Math.abs(humidityMap[i+1][j]) == 0 || humidityMap[i][j] != humidityMap[Math.abs(i-1)][j] && Math.abs(humidityMap[i][j]) - Math.abs(humidityMap[Math.abs(i-1)][j]) == 0 || humidityMap[i][j] != humidityMap[i][j+1] && Math.abs(humidityMap[i][j]) - Math.abs(humidityMap[i][j+1]) == 0 || humidityMap[i][j] != humidityMap[i][Math.abs(j-1)] && Math.abs(humidityMap[i][j]) - Math.abs(humidityMap[i][Math.abs(j-1)]) == 0){
                    humidityMap[i][j] = 0;
                }
            } 
        }
        
            /*
        for(int i = 0; i < size; i++){
            System.out.println(Arrays.toString(humidityMap[i]).replaceAll("-1","H").replaceAll("1","A").replaceAll("0","N"));
        } */
        
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(map[i][j] == 1 && humidityMap[i][j] == 1){
                    totalMap[i][j] = "DES";
                } else if (map[i][j] == 1 && humidityMap[i][j] == 0){
                    totalMap[i][j] = "SAV";
                } else if (map[i][j] == 1 && humidityMap[i][j] == -1){
                    totalMap[i][j] = "JUN";
                } else if(map[i][j] == 0 && humidityMap[i][j] == 1){
                    totalMap[i][j] = "PLA";
                } else if(map[i][j] == 0 && humidityMap[i][j] == 0){
                    totalMap[i][j] = "PLA";
                } else if(map[i][j] == 0 && humidityMap[i][j] == -1){
                    totalMap[i][j] = "FOR";
                } else if(map[i][j] == -1 && humidityMap[i][j] == 1){
                    totalMap[i][j] = "TUN";
                } else if(map[i][j] == -1 && humidityMap[i][j] == 0){
                    totalMap[i][j] = "TAI";
                } else if(map[i][j] == -1 && humidityMap[i][j] == -1){
                    totalMap[i][j] = "SNO";
                } else {
                    totalMap[i][j] = "   ";
                }
            }
        }
        for (int c = 0; c < size; c++){
            System.out.println(Arrays.toString(totalMap[c]));
        }
    }
     
     private static int[][] setValuePlus (int[][] map, int radius, int size, int maxSize){
        int x = addDroplet(size, maxSize);
        int z = addDroplet(size, maxSize);
        for(int i = 0-radius; i < radius; i++){
            for(int j = 0-radius; j < radius; j++){
                if(Math.sqrt(Math.abs(Math.pow(i, 2))+Math.abs(Math.pow(j, 2)))<radius){
                    map[Math.abs(i+x)][Math.abs(j+z)] = addOrReplace(map[Math.abs(i+x)][Math.abs(j+z)]);
                }
            }
        }
        return map;
     }
     
     private static int[][] setValueMin (int[][] map, int radius, int size, int maxSize){
        int x = addDroplet(size, maxSize);
        int z = addDroplet(size, maxSize);
        for(int i = 0-radius; i < radius; i++){
            for(int j = 0-radius; j < radius; j++){
                if(Math.sqrt(Math.abs(Math.pow(i, 2))+Math.abs(Math.pow(j, 2)))<radius){
                    map[Math.abs(i+x)][Math.abs(j+z)] = subtractOrReplace(map[Math.abs(i+x)][Math.abs(j+z)]);
                }
            }
        }
        return map;
     }
     
     private static int addDroplet(int size, int maxSize){
        return (int)(Math.random()*(size-maxSize));
        }
     
     private static int subtractOrReplace(int in){
         return in>-1?in-1:-1;
     }
     
     private static int addOrReplace(int in){
         return in<1?in+1:1;
     }
     
     
     public static void main(String []args){
        heatMap(112, 50, 500);
        System.out.println("\n");
     }
}
