/**
 * 
 */
package mmatic.model;

import java.util.ArrayList;

/**
 * Diese Klasse erstellt einen Objekt WortListe
 * und bietet verschiedene Methoden den zu bearbeiten
 * @author Milos Matic
 * @version 08.09.2022
 */
public class TrainerListe {
	private ArrayList<Paar> worteintraegen = new ArrayList<>();
	
	/**
	 * Erzeugt ein neuses Paar mit der ueberegebenen Parameter und gibt diese mithilfe von addPaar Methode in der array
	 * @param wort
	 * @param url
	 * @throws IllegalArgumentException wenn der Parameter ungueltig ist
	 */
	public void addPaar(String wort, String url) throws IllegalArgumentException{
		worteintraegen.add(new Paar(wort, url));
		Paar worteintrag = new Paar(wort, url);
		Paar[] temp = new Paar[worteintraegen.length+1];
		for(int i = 0; i < worteintraegen.length; i++) {
			temp[i] = worteintraegen[i];
		}
		temp[temp.length-1] = worteintrag;
		worteintraegen = temp;
	}
	
	/**
	 * Gibt einen Worteintrag am uebergebenem Platz zurueck
	 * @param i uebergebene Platz
	 * @return worteintrag am uebergebenem Platz
	 */
	public Paar getPaar(int i) {
		return worteintraegen[i];
	}
	
	/**
	 * Zucht den Worteintrag der das gleiche wort hat wie der uebergebene Parameter und loescht den
	 * @param wort
	 * @return true bei Erfolg
	 */
	public boolean deletePaar(String wort) throws IllegalArgumentException{
		if(wort != null) {
			int anzahl = 0;
			for (int i = 0; i < worteintraegen.length; i++) {
				if(worteintraegen[i].getWort().equals(wort)) {
					worteintraegen[i] = null;
					anzahl++;
				}
			}
			Paar[] temp = new Paar[worteintraegen.length-anzahl];
			for(int i = 0, j = 0; j < worteintraegen.length; j++) {
				if(worteintraegen[j] == null) {
					continue;
				}
				else {
					temp[i] = worteintraegen[j];
					i++;
				}
			}
			worteintraegen = temp;
			
			return anzahl > 0;
		}
		throw new IllegalArgumentException("Der Objekt String muss erzeugt werden.");
	}
	
	/**
	 * Fasst alle WortEintraege in einem string zusammen
	 * @return zusammengefasste text
	 */
	@Override
	public String toString() {
		String text = "";
		for (int i = 0; i < worteintraegen.length-1; i++) {
			text += worteintraegen[i].toString() + "\n";
		}
		text += worteintraegen[worteintraegen.length-1].toString();
		return text;
	}
	
	/**
     * 
	 * @return 
	 */
	public int length() {
		return worteintraegen.length;
	}
}
