public class Solver {
    public Balle[] balles;
    public int nbBalles;
    public Grid grille;

    public Solver(int nbMax, Grid grille){
        this.balles = new Balle[nbMax];
        this.nbBalles=0;
        this.grille = grille;
    }

    public void addBalle(Balle b){
        balles[nbBalles]=b;
        nbBalles++;
        b.id=nbBalles;
    }

    public void update(double dt){
        int sub_step =8;
        double sub_dt = dt/sub_step;
        calculGrid();

        //for (int i = 0; i < grille.size; i++) {
            //for (int j = 0; j < grille.size; j++) {
                //if(grille.grille[i][j].balles.size()!=0){
                    //System.out.println(grille.grille[i][j].balles.size());
                //}
           // }
        //}
        //System.out.println("");
        
        for(int i=0; i<sub_step; i++){
            
            applyConstraint();
            solveCollisions();
            updatePositions(sub_dt);
            
        }

        grille.clear();
    }

    public void calculGrid(){
        for(int i=0;i<nbBalles; i++){
            Vec2 tile = balles[i].calculGrille(grille.size);
            grille.addBalle(tile, balles[i]);
        }
    }

    public void updatePositions(double dt){
        for(int i=0; i<nbBalles; i++){
            balles[i].update_position(dt);
        }
    }

    public void applyConstraint(){
        for(int i=0; i<nbBalles; i++){
            balles[i].applyConstraint();
        }
    }

    public void solveCollisions(){
        for (int i=0; i<nbBalles; i++){
            Balle balle_a = balles[i];

            int caseX= balle_a.tile_x;
            int caseY= balle_a.tile_y;

            int startX = Math.max(0, caseX - 1);
            int startY = Math.max(0, caseY - 1);
            int endX = Math.min(grille.size-1, caseX + 1);
            int endY = Math.min(grille.size-1, caseY + 1);


            for (int a = startX; a <= endX; a++) {
                for (int b = startY; b <= endY; b++) {
                    
                    Tile currentCase = grille.grille[a][b];
                    for(int index : currentCase.balles) {
                        balle_a.solve_collision(balles[index-1]);
                    }
                }
            }
        }
    }
}
