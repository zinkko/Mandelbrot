/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandel;

/**
 *
 * @author Ilari
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import static mandel.Direction.*;

public class Kuuntelija implements KeyListener{
    
    private Fraktaali fraktaali;
    private UserInterface ui;
    
    public Kuuntelija(Fraktaali f, UserInterface ui){
        this.fraktaali = f;
        this.ui = ui;
    }
    
    @ Override
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_PLUS: this.fraktaali.zoomIn();
                break;
            case KeyEvent.VK_MINUS: this.fraktaali.zoomOut();
                break;
            case KeyEvent.VK_LEFT: this.fraktaali.nudge(LEFT);
                break;
            case KeyEvent.VK_RIGHT: this.fraktaali.nudge(RIGHT);
                break;
            case KeyEvent.VK_UP: this.fraktaali.nudge(UP);
                break;
            case KeyEvent.VK_DOWN: this.fraktaali.nudge(DOWN);
                break;
            case KeyEvent.VK_ENTER:
                this.fraktaali = new Fraktaali();
                this.ui.reset(fraktaali);
                break; 
            case KeyEvent.VK_S: this.fraktaali.saveImage();
                break;
        }
        
        fraktaali.repaint();
    }
    
    @ Override
    public void keyReleased(KeyEvent e){
        
    }
    
    @ Override
    
    public void keyTyped(KeyEvent e){
        
    }
    
    
}
