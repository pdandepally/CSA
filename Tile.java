public class Tile {
    
    private int value;
    private boolean open;
    
    public Tile(int num){
        
        value = num;
        open = true;
        
    }
    
    public int getVal(){
        return value;
    }
    
    public boolean getStatus(){
        return open;
    }
    
    public void close(){
        open = false;
    }
    
}