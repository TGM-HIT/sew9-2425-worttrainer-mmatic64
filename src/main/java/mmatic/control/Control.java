/**
 * 
 */
package mmatic.control;

import mmatic.model.*;
import mmatic.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Diese Klasse verbindent den Model package mit dem View Packege
 *
 * @author Milos Matic
 * @version 04.10.2024
 */
public class Control implements ActionListener{
	private Trainer trainer;
	private Speicher speicher;
	private Panel panel;
	
	public Control() {
		speicher = new Speicher(new SelbstDefiniert());
		try {
			trainer = speicher.laden();
		} catch (IOException e) {
			System.err.println("Default Trainer nicht gefunden");
			trainer = new Trainer();

			trainer.addPaar(new Paar("orf", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/ORF_logo.svg/1200px-ORF_logo.svg.png"));
			trainer.addPaar(new Paar("text", "https://codrops-1f606.kxcdn.com/codrops/wp-content/uploads/2015/02/TextFill_image3.png?x62774"));
			trainer.addPaar(new Paar("gmail", "https://akm-img-a-in.tosshub.com/businesstoday/images/story/201904/gmail-660_040119014433.jpg?size=948:533"));
		}
		trainer.select();
		panel = new Panel(trainer.getBild().getURL(), this);
		panel.setStatistic(trainer.getRichtig(), trainer.getAbgefragt());
		new Frame("titel", panel);
	}

	/**
	 * Diese Methode dient als ein ActionListener macht entsprechend der ActionCommand eine aufgabe
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		
		//wenn ein Wort geraten wird und enter gedrueckt ist
		if(ac.equals("eingabe")) {
			//wenn richtig wird die Anzahl der Richtige und der Abgefragte vergroessert
			if(trainer.guessIgnoreCase(panel.getEingabe())) {
				System.out.println("true");
				panel.setLast("Richtig");
				refresh();
			}
			//wenn falsch wird nur die Anzahl der Abgefragte vergroessert
			else {
				System.out.println("false");
				panel.setLast("Falsch");
			}
			panel.setStatistic(trainer.getRichtig(), trainer.getAbgefragt());
		}
		//wenn die Taste Zuruecksetzen gedrueckt wird
		else if(ac.equals("zuruecksetzen")) {
			//die gezaehlte Zahlen werden wieder auf 0 zurueckgesetzt
			trainer.zuruecksetzen();
			panel.setStatistic(0, 0);
		}
		//wenn die Taste Hinzufuegen gedrueckt wird um die
		else if(ac.equals("hinzufuegen")) {
			JFrame f = new JFrame();
			String wort = JOptionPane.showInputDialog(f, "Wort einfuegen");
			String url = JOptionPane.showInputDialog(f, "URL einfuegen");
			try {
				trainer.addPaar(wort, url);
			}catch(IllegalArgumentException e1){
				JOptionPane.showMessageDialog(f, e1.getMessage());
			}
		}
		else if(ac.equals("speichern")) {
			try {
				speicher.speichern(trainer);
			} catch (IllegalArgumentException | IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(ac.equals("laden")) {
			try {
				trainer = speicher.laden();
				refresh();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Die Mehtode zeigt im Panel ein neu zufaellig gewaehltes Bild aus den Trainer
	 */
    public void refresh(){
    	trainer.select();
    	this.panel.setBild(this.trainer.getBild().getURL());
		panel.setStatistic(trainer.getRichtig(), trainer.getAbgefragt());
    }

	public static void main(String[] args) {
		new Control();
	}
}
