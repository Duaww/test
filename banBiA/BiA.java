package banBiA;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;   

public class BiA extends JFrame 
{
    public static void main(String[] args) {
        new BiA();
    }

    static int w = 550;
    static int h = 350;
    static int m = 50;
    BufferedImage img;
    Graphics g;
    Ball bs[] = new Ball[100];
    Random rand = new Random();

    public BiA() {
        this.setTitle("ban ban bi a");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(w + 2 * m, h + 2 * m);
        
        img = new BufferedImage(w+2*m,h+2*m, BufferedImage.TYPE_3BYTE_BGR);
        g = img.getGraphics();
        for (int i = 0; i < bs.length; i++) {
            double r = rand.nextDouble() * 15 + 5;
            double x = rand.nextDouble() * (w - 2 * r) + r;
            double y = rand.nextDouble() * (h - 2 * r) + r;
            double vx = rand.nextDouble() - 0.5;
            double vy = rand.nextDouble() - 0.5;
            bs[i] = new Ball(x, y, r, vx, vy);
            bs[i].start();
        }
        this.setVisible(true);
    }

    public void paint(Graphics g1) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(m, m, w, h);
        g.setColor(Color.BLUE);
        g.drawRect(m, m, w, h);

        for (int i = 0; i < bs.length; i++) {
            int x = (int) (bs[i].x+m+0.5);
            int y = (int) (bs[i].y+m+0.5);
            int r = (int) (bs[i].r+0.5);
            g.setColor(Color.YELLOW);
            g.fillOval(x - r, y - r, r * 2, r * 2);
            g.setColor(Color.BLUE);
            g.drawOval(x - r, y - r, r * 2, r * 2);
        }
        g1.drawImage(img, 0, 0, null);
        repaint();

    }
}

class Ball extends Thread {
    double x, y;
    double r;
    double vx, vy;

    public Ball(double x, double y, double r, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void run() 
    {
        while (true) 
        {
            x+=vx;
            y+=vy;
            if ((x-r<=0 && vx<0)||(x+r>=BiA.w)&&(vx>0)) vx = -vx; 
           
            if ((y - r<=0 && vy<0)||(y+r>=BiA.h)&&(vy>0))  vy = -vy; 
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }
    }

}