package mmatic.view;

import mmatic.control.Control;
import mmatic.model.Paar;
import mmatic.model.SelbstDefiniert;
import mmatic.model.Speicher;
import mmatic.model.Trainer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Diese Klasse zeigt alle Tasten und Textfields um WortTrainer zu verwenden
 *
 * @author Milos Matic
 * @version 04.10.2024
 */
public class Panel extends JPanel {
    private JTextField eingabe;
    private JLabel bild;
    private JLabel abgefragt, richtig, last;
    private JButton zuruecksetzen, hinzufuegen;
    private JButton laden, speichern;
    private Image image;

    private Control control;
    
    public Panel(String bildPfad, Control control) {
    	this.control = control;

        this.setLayout(new BorderLayout());
        
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.PAGE_AXIS));
        
        JLabel frage = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum ueberpruefen)?");
        eingabe = new JTextField();
        eingabe.setActionCommand("eingabe");
        eingabe.addActionListener(this.control);

        panelNorth.add(frage);
        panelNorth.add(eingabe);

        this.setBildPfad(bildPfad);
        bild = new JLabel(new ImageIcon(image));
        
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridLayout(4,3));

        richtig = new JLabel("0");
        zuruecksetzen = new JButton("Zuruecksetzen");
        zuruecksetzen.setActionCommand("zuruecksetzen");
        zuruecksetzen.addActionListener(this.control);

        abgefragt = new JLabel("0");
        hinzufuegen = new JButton("Wort hinzufuegen");
        hinzufuegen.setActionCommand("hinzufuegen");
        hinzufuegen.addActionListener(this.control);

        last = new JLabel("");

        speichern = new JButton("Speichern");
        speichern.setActionCommand("speichern");
        speichern.addActionListener(this.control);
        laden = new JButton("Laden");
        laden.setActionCommand("laden");
        laden.addActionListener(this.control);

        panelSouth.add(new JLabel("Richtige Woerter:"));
        panelSouth.add(richtig);
        panelSouth.add(new JLabel(""));

        panelSouth.add(new JLabel("Anzahl Woerter:"));
        panelSouth.add(abgefragt);
        panelSouth.add(zuruecksetzen);

        panelSouth.add(new JLabel("Letzte Antwort war:"));
        panelSouth.add(last);
        panelSouth.add(hinzufuegen);
        
        panelSouth.add(speichern);
        panelSouth.add(new JLabel(""));
        panelSouth.add(laden);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(bild, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);
    }

    /**
     * 
     * @return text der im eingabe Textfeld eingetragen ist
     */
    public String getEingabe() {
        return eingabe.getText();
    }

    /**
     * Wird aufgerufen wenn das Bild richtig geraten wurde, um die zwei Label, Richtig und Abgefragt, zu aendern
     * @param richtig, auf welche Zahl das Label zu aendern ist
     * @param abgefragt, auf welche Zahl das Label zu aendern ist
     */
    public void setStatistic(int richtig, int abgefragt) {
        this.richtig.setText(Integer.toString(richtig));
        this.abgefragt.setText(Integer.toString(abgefragt));
    }

    public void setLast(String last) {
    	this.last.setText(last);
    }
    
    /**
     * Aendert den Pfad des Bildes ohne sie zu aendern
     * @param bildPfad
     */
    private void setBildPfad(String bildPfad) {
        try {
            ImageIcon icon = new ImageIcon(new URL(bildPfad));
            image = icon.getImage();
            image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		} catch (Exception e) {
			System.out.println("Image not Found");
		}
    }
    /**
     * Wird aufgerufen wenn das Bild geaendert werden soll
     * @param bildPfad, Pfad des neues Bildes
     */
    public void setBild(String bildPfad) {
    	setBildPfad(bildPfad);
        bild.setIcon(new ImageIcon(image));
    }    
}
