public class Vec2 {
    public double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void affiche() {
        System.out.println("x : " + x + ", y : " + y);
    }

    public static Vec2 plus(Vec2 a, Vec2 b) {
        return new Vec2(a.x + b.x, a.y + b.y);
    }

    public static Vec2 plus(Vec2 a, Vec2 b, Vec2 c) {
        return new Vec2(a.x + b.x + c.x, a.y + b.y + c.y);
    }

    public static Vec2 moins(Vec2 a, Vec2 b) {
        return new Vec2(a.x - b.x, a.y - b.y);
    }

    public static Vec2 fois(Vec2 a, Vec2 b) {
        return new Vec2(a.x * b.x, a.y * b.y);
    }

    public Vec2 fois(double a) {
        return new Vec2(x * a, y * a);
    }

    public static double distance(Vec2 a, Vec2 b) {

        // h² = a²+b²

        return Math.sqrt(Math.abs(a.x - b.x) * Math.abs(a.x - b.x) + Math.abs(a.y - b.y) * Math.abs(a.y - b.y));
    }

    public boolean equals(Vec2 a){
        return (x==a.x && y==a.y);
    }

}
