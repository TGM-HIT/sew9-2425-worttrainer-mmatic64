/**
 * 
 */
package mmatic.model;

/**
 * Diese Klasse erstellt einen Objekt Paar
 * und bietet verschiedene Methoden den zu bearbeiten
 * @author Milos Matic
 * @version 27.09.2024
 */
public class Paar {
	private String wort, url;

	/**
	 * Konstruktor fuer ganze erzeugung
	 * @param wort
	 * @param url
	 */
	public Paar(String wort, String url) throws IllegalArgumentException{
		this.setWort(wort);
		this.setURL(url);
	}

	/**
	 * Ueberprueft ob der url Sinnvoll ist (bettint mit "https://" oder "http://" gefolgt von einem Buchstaben, danach ein Punkt und wieder ein Buchstabe)
	 * @param url
	 * @return true wenn url sinnvoll ist
	 * @throws IllegalArgumentException wenn 
	 */
	public static boolean checkUrl(String url) throws IllegalArgumentException{
		if(url != null) {
			boolean ende = false;
			int i;
			if(url.startsWith("https://")) {
				i = 7;
			}
			else if(url.startsWith("http://")) {
				i = 6;
			}
			else {
				throw new IllegalArgumentException("URL beginnt nicht mit \"http://\" oder \"https://\"!");
			}
			if(!(url.length() > i+1  && url.charAt(i+1) != '.')) {
				throw new IllegalArgumentException("URL muss etwas nach \"//\" haben!");
			}
			do {
				i++; 
				if(url.charAt(i) == '.') {
					i++;
					ende = true;
					if(url.charAt(i) == '.') {
						throw new IllegalArgumentException("URL kann nicht zwei aufeinander folgende \".\" haben!");
					}
				}
			}while(i < url.length()-2);
			i = url.length()-1;
			if(url.charAt(i) == '.') {
				throw new IllegalArgumentException("URL darf sich nicht mit \".\" enden!");
			}
			if(ende)
				return ende;
			throw new IllegalArgumentException("URL hat kein \".\"");
		}
		throw new IllegalArgumentException("Ungueltige Eingabe");
	}
	
	/**
	 * Ueberprueft ob der Parameter ein Buchstabe ist
	 * @param a
	 * @return true wenn der Parameter eine buchstabe ist
	 */
	private static boolean istBuchstabe(char a) {
		return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
	}

	
	/**
	 * @return wort
	 */
	public String getWort() {
		return wort;
	}

	/**
	 * @param wort
	 */
	public void setWort(String wort) {
		if(wort != null) {
			if(wort.length() < 2) {
				throw new IllegalArgumentException("Wort muss mindestens 2 Buchstaben haben");
			}
			if(!Paar.istBuchstabe((wort.charAt(0))) &&  !Paar.istBuchstabe((wort.charAt(1)))) {
				throw new IllegalArgumentException("Wort muss mindestens 2 Buchstaben haben");
			}
			this.wort = wort;
		} else {
			throw new IllegalArgumentException("Ungueltige Eingabe");
		}
	}

	/**
	 * @return url
	 */
	public String getURL() {
		return url;
	}

	/**
	 * @param url
	 */
	public void setURL(String url) {
		Paar.checkUrl(url);
		this.url = url;
	}

	/**
	 * Fasst alle Attribute in einem String zusammen in form: wort: url
	 * @return zusammengefasste text
	 */
	@Override
	public String toString() {
		return wort + "; " + url;
	}
}
