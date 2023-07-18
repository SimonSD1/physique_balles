import java.util.ArrayList;

public class Tile {
    public ArrayList<Integer> balles;
    int nbBalles;

    public Tile(){
        this.balles = new ArrayList<Integer>();
    }

    public int getNbBalles(){
        return nbBalles;
    }

    public void addBalle(Balle b){
        if (!balles.contains(b.id)) {
        balles.add(b.id);
        nbBalles++;
        }
    }

    public void clear(){
        balles.clear();
        nbBalles=0;
    }
}
