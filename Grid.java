public class Grid {
    public Tile[][] grille;
    public int size;

    public Grid(int size){
        this.grille = new Tile[size][size];
        this.size=size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grille[i][j] = new Tile();
            }
        }
    }

    public void addBalle(Vec2 pos, Balle b){
        grille[(int)pos.x][(int)pos.y].addBalle(b);
    }

    public void clear(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                grille[i][j].clear();
            }
        }
    }

    public void affiche(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(grille[i][j].nbBalles!=0){
                    System.out.println(grille[i][j].nbBalles);
                }
            }
        }
    }
}
