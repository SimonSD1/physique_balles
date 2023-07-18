import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable{
    Thread gameThread;
    final int screenWidth = 800;
    final int screenHeight = 600;
    final double DT = 0.016666667;
    Vec2 contrainte_pos=new Vec2(800 / 2, 600 / 2);
    double contrainte_rayon=250;
    Solver solver;
    int nbCase;
   

    public GamePanel(Solver solver, int nbCase) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.solver=solver;
        this.nbCase=nbCase;
    }

    public void startGameThread() {
        gameThread = new Thread(this); // lance thread du panel
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 * DT; // pour 60 fps
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            update();
            repaint();
            
            
            
            try {
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime / 1000000;
                if (remainTime < 0) {
                    remainTime = 0;
                }
                Thread.sleep((long) remainTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

    public void update(){
        solver.update(DT);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < nbCase; i++) {
            for (int j = 0; j < nbCase; j++) {
                g2.setColor(Color.BLUE);
                g2.fillRect(j * 800 / nbCase, i * 600 / nbCase, 800 / nbCase - 1, 600 / nbCase - 1);
            }
        }

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillOval((int) (contrainte_pos.x-contrainte_rayon), (int) (contrainte_pos.y-contrainte_rayon), (int)contrainte_rayon*2, (int)contrainte_rayon*2);

        g2.setStroke(new BasicStroke(2));

        for (int i=0; i<solver.nbBalles; i++) {
            if (solver.balles[i] != null) {
                g2.setColor(Color.RED);
                g2.fillOval((int) (solver.balles[i].pos.x - solver.balles[i].rayon), (int) (solver.balles[i].pos.y - solver.balles[i].rayon), (int) solver.balles[i].rayon * 2, (int) solver.balles[i].rayon * 2);
                g2.setColor(Color.BLACK);
                g2.drawOval((int) (solver.balles[i].pos.x - solver.balles[i].rayon), (int) (solver.balles[i].pos.y - solver.balles[i].rayon), (int) solver.balles[i].rayon * 2, (int) solver.balles[i].rayon * 2);
            }
        }
    }
}
