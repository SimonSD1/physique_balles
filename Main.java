import javax.swing.JFrame;

public class Main{

    public static void main(String[] args) throws InterruptedException {
        final int screenWidth = 800;
        final int screenHeight = 600;
        final int nbMax=2400;
        final int rayon = 3;
        final int nbCase = (int)200/rayon;

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Physique");
        window.setSize(screenWidth, screenHeight);

        Grid grille = new Grid(nbCase);
        Solver solver = new Solver(nbMax, grille);

        GamePanel panel = new GamePanel(solver, nbCase);

        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panel.startGameThread();


        for(int i=0; i<nbMax; i++){
            solver.addBalle(new Balle(new Vec2(screenWidth/2 - 110, screenHeight/2), rayon));
            Thread.sleep((long) 10);
        }
    }
    
}