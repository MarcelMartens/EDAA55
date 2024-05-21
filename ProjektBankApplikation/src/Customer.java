
public class Customer {
	private String name;
	private long idNr;
	private int kundNr;
	private static int counter = 100;
	

	/**
	 * Skapar en kund (kontoinnehavare) med namnet 'name' och id-nummer 'idNr'.
	 * Kunden tilldelas också ett unikt kundnummer.
	 */
	Customer(String name, long idNr) {
		this.name = name;
		this.idNr = idNr;
		this.kundNr = counter;
		counter++;
		// Glöm ej kundnummer

	}

	/** Tar reda på kundens namn. ingen modifier på acces */
	String getName() {
		return this.name;
	}

	/** Tar reda på kundens personnummer. */
	long getIdNr() {
		return this.idNr;
	
	}
	/** Tar reda på kundens kundnummer. */
	int getCustomerNr() {
		return this.kundNr;
	}

	/** Returnerar en strängbeskrivning av kunden. */
	public String toString() {
		String toString = "namn: "+this.name+", personnummer: "+this.idNr+", kundnummer: "+kundNr;
		return toString;
	}

}
