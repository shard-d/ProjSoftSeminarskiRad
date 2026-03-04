
package ui.general;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author danic
 */
public class Notification {
    public static void showInfoMessage(JFrame parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showErrorMessage(JFrame parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
