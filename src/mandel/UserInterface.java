/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandel;

/**
 *
 * @author Ilari
 */
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
//import java.awt.Component;

public class UserInterface implements Runnable{
    
    private JFrame frame;
    private Hiirenkuuntelija k;
    
    public UserInterface(){
        this.frame = new JFrame();
    }
    
    @Override 
    public void run(){
        frame.setPreferredSize(new Dimension(820,560));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
    private void luoKomponentit(Container container){
        Fraktaali f = new Fraktaali();
        container.add(f);
        this.k = new Hiirenkuuntelija(f);
        f.addMouseListener(k);
        
        frame.addKeyListener(new Kuuntelija(f,this));
    }
    
    public void reset(Fraktaali f){
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(f);
        this.k.reset(f);
    }
}
