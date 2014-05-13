/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Ilari
 */
public class Hiirenkuuntelija implements MouseListener{
    
    private Fraktaali fraktaali;
    
    public Hiirenkuuntelija(Fraktaali f){
        this.fraktaali = f;
    }
    
    @Override
    
    public void mouseEntered(MouseEvent e){
        //System.out.println("here comes the mouse!");
    }
    
    @Override
    
    public void mouseExited(MouseEvent e){
        //System.out.println("Hello, I'm the mouse!");
    }
    
    @ Override
    
    public void mousePressed(MouseEvent e){
        //System.out.println("pressed");
    }
    
    @ Override 
    
    public void mouseReleased(MouseEvent e){
        //System.out.println("released");
    }
    
    @ Override 
    public void mouseClicked(MouseEvent e){
        
        if (e.getX() > fraktaali.getLeveys() || e.getY() > fraktaali.getPituus()){
            return;
        }
        
        if (e.getButton() == MouseEvent.BUTTON1){
            fraktaali.zoomIn(e.getX(), e.getY());
        }else{
            fraktaali.zoomOut(e.getX(),e.getY());
        }
        
        fraktaali.repaint();
        
    }
    
    public void reset(Fraktaali f){
        this.fraktaali = f;
    }
}
