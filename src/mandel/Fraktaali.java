/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandel;

/**
 *
 * @author Ilari
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Fraktaali extends JPanel {

    private final int leveys;
    private final int pituus;
    private int isoLuku;
    private double reCenter, imagCenter, unit;

    private HashMap<Integer, Color> palette;

    public Fraktaali() {
        this.leveys = 819; //1365;
        this.pituus = 546; //910;
        this.isoLuku = 200;
        this.reCenter = -0.5;
        this.imagCenter = 0;
        this.unit = 3 / (double) leveys;
    }

    public void setIteration(int maara) {
        this.isoLuku += maara;
    }

    private int belongsInBox(double x, double y) {
        double rez = 0;
        double imz = 0;
        int palaute = 0;

        for (int i = 0; i < isoLuku; i++) {
            double old = rez;

            rez = (rez * rez) - (imz * imz) + x;
            imz = old * imz * 2 + y;

            if ((rez * rez + imz * imz) > 4) {
                return palaute;
            }
            palaute++;
        }
        return palaute;
    }

    public void setPalette() {
        if (this.palette == null) {
            this.palette = new HashMap<>();
        } else {
            this.palette.clear();
        }
        Color gradientBaseColor = Color.CYAN;

        /*for (int i=0; i<= isoLuku; i++){
         int factor = Math.abs(i-isoLuku/2);
         Color newColor = gradientBaseColor;
         for (int j=0; j< factor; j++){
         newColor = newColor.darker();
         }
         this.palette.put(i,newColor);
         }*/
        for (int i = 0; i <= isoLuku; i++) {
            double factor = Math.abs(i - isoLuku / 2.0);

            factor /= 4.0;

            if (factor < 1) {
                factor = 1;
            }

            double red = gradientBaseColor.getRed() / 255.0;
            double green = gradientBaseColor.getGreen() / 255.0;
            double blue = gradientBaseColor.getBlue() / 255.0;

            palette.put(i, new Color((float) (red / factor), (float) (green / factor), (float) (blue / factor)));
        }

        palette.put(isoLuku, Color.BLACK);

    }
    
    public void nudge(Direction direction){
        this.reCenter += unit*direction.x;
        this.imagCenter += unit*direction.y;
    }

    public void saveImage() {
        int width = 1366;
        int height = 768;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        File out = new File(System.currentTimeMillis()+".png");

        if (this.palette == null) {
            this.setPalette();
        }

        int iterations;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                double rez = reCenter + unit * (i - leveys / 2);
                double imz = imagCenter + unit * (j - pituus / 2);
                
                iterations = this.belongsInBox(rez, imz);
                int rgb = this.palette.get(iterations).getRGB();
                image.setRGB(i, j, rgb);
            }
        }
        try {
            ImageIO.write(image, "png", out);
            System.out.println("Saved!");
        } catch (IOException ex) {
            Logger.getLogger(Fraktaali.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void zoomIn(int x, int y){
        this.reCenter = getFractalX(x);
        this.imagCenter = getFractalY(y);
        
        zoomIn();
    }
    
    public void zoomIn(){
        this.unit /= 1.5;
    }
    
    public void zoomOut(int x, int y){
        this.reCenter = getFractalX(x);
        this.imagCenter = getFractalY(y);
        
        zoomOut();
    }
    
    public void zoomOut(){
        this.unit *= 1.5;
    }
    
    private double getFractalX(int relativeX){
        return reCenter + unit * (relativeX - leveys / 2);
    }
    
    private double getFractalY(int relativeY){
        return imagCenter + unit * (relativeY - pituus / 2);
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        if (this.palette == null) {
            this.setPalette();
            System.out.println("palette was null, made new one");
        }

        g.setColor(Color.black);

        int iterations;

        for (int i = 0; i <= leveys; i++) {
            for (int j = 0; j <= pituus; j++) {
                double rez = getFractalX(i);
                double imz = getFractalY(j);

                iterations = this.belongsInBox(rez, imz);

                g.setColor(this.palette.get(iterations));
                g.drawLine(i, j, i, j);

            }
        }

        System.out.println("hi");
    }

    public double getReCenter() {
        return reCenter;
    }

    public double getImagCenter() {
        return imagCenter;
    }

    public double getUnit() {
        return unit;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getPituus() {
        return pituus;
    }

}

enum Direction{
    LEFT(-10,0),RIGHT(10,0),UP(0,-10),DOWN(0,10);
    
    int x;
    int y;
    
    private Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
}

