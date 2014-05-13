/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandel;

/**
 *
 * @author Ilari
 */
import javax.swing.SwingUtilities;

public class Mandel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);
    }
}
