/**
 * 
 */
package mmatic.view;

import javax.swing.*;

/**
 * Diese Klasse dient als Fenster, um den Panel zu zeigen
 *
 * @author Milos Matic
 * @version 04.10.2024
 */
public class Frame extends JFrame {
    private Panel panel;
    
    public Frame(String titel, Panel panel) {
        super(titel);
        this.panel = panel;
        
        this.add(this.panel);

        this.setBounds(100, 200, 500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
