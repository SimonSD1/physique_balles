public class Balle {
    public Vec2 pos;
    public Vec2 pos_old;
    public Vec2 acceleration;
    public int rayon;
    public int id;
    public int tile_x;
    public int tile_y;

    public Balle(Vec2 pos, int rayon){
        this.pos_old=pos;
        this.pos=pos;
        this.acceleration = new Vec2(0, 700);
        this.rayon=rayon;
    }

    public void update_position(double dt){
        Vec2 vitesse = Vec2.moins(pos, pos_old);
        pos_old = pos;
        pos = Vec2.plus(pos, vitesse, acceleration.fois(dt * dt));
    }

    public void accelerate(Vec2 acc) {
        acceleration = Vec2.plus(acceleration, acc);
    }

    public void solve_collision(Balle b){
        double distance = Vec2.distance(pos, b.pos);
        if (distance < rayon * 2) {
            Vec2 diff = (Vec2.moins(b.pos,pos).fois((rayon * 2 - distance) /rayon /2)).fois(0.5);
            pos = Vec2.moins(pos, diff);
            b.pos = Vec2.plus(b.pos, diff);
        }
    }

    public void applyConstraint(){
        Vec2 contrainte_pos=new Vec2(800 / 2, 600 / 2);
        double contrainte_rayon=250;

        double distance = Vec2.distance(pos, contrainte_pos);

        if (distance + rayon > contrainte_rayon ) {
            Vec2 point_plus_proche = Vec2.moins(contrainte_pos, pos)
                    .fois((distance - (contrainte_rayon - rayon)) / distance );
            pos = Vec2.plus(pos, point_plus_proche);
        }
    }

    public Vec2 calculGrille(int size){
        tile_x  = (int) (pos.x / (800 / size))%size;
        tile_y = (int) (pos.y / (600 / size))%size;
        return new Vec2(tile_x,tile_y);
    }
}
