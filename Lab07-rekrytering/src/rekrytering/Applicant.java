package rekrytering;

import java.util.Arrays;

public class Applicant implements Comparable<Applicant> {

	// Varje sökande har ett namn och ett antal betyg
	private String name;
	private int[] grades;


	public String getName() {

		return name;
	}


	public int[] getGrades() {

		return grades;
	}


	// konstruktor för ett Applicant-objekt.
	// sätter namnet till inmatningen och ändrar betyget till en string för att kunna lägga till den
	// i en vektor
	public Applicant(String name, String gradesAsString) {

		this.name = name;
		parseGrades(gradesAsString);
	}


	// Metod för att omvandla betyg till siffror för att kunna jämföra och sortera
	private void parseGrades(String gradesAsString) {

		// gradesAsString har formatet x,y,z.... där respektive bokstav är ett betyg
		// Om vi splittar strängen på komma (",") hamnar varje betyg i en vektor
		String[] g = gradesAsString.split(",");
		// Skapa vektorn med heltal
		this.grades = new int[g.length];
		// Iterera över alla betyg för att översätta dessa till ett heltal
		for (int i = 0; i < g.length; i++) {
			if (g[i].equals("U")) {
				// Om underkänd så räknar vi det som en nolla
				this.grades[i] = 0;
			} else {
				this.grades[i] = Integer.parseInt(g[i]);
			}
		}
	}


	// Metod för att beräkna medelbetyget av en sökande.
	// Adderar alla betygen och delar med 5
	public double getAvgGrade() {

		int[] grade = this.grades;
		double avgGrade = 0;
		for (int i : grade) {
			avgGrade += i;
		}
		avgGrade /= 5;
		return avgGrade;
	}


	// Omvandlar ett applicant-objekt till en String för att kunna skriva ut det i konsollen
	public String applicantToString() {

		String applicantToString = (this.getName() + "  -  " + Arrays.toString(this.getGrades()) + "  (avg: "
				+ this.getAvgGrade() + ")");
		return applicantToString;
	}


	// Metod för att jämföra detta Applicant-objekt med ett annat och få ut vilket som är störst.
	// Retunerar något < 0 om detta objektet är störst. Returnerar något > 0 om other är störst och
	// returnerar 0 om objekten är lika. Används av javas inbyggda sorteringsmetoder
	public int compareTo(Applicant other) {

		// Om other är exakt samma objekt
		if (other == this) {
			return 0;
		}
		// Jämför snittbetygen av valda applicant-objekt
		int gradeRes = (int) Math.round((((Applicant) other).getAvgGrade() - getAvgGrade()) * 100);
		if (gradeRes == 0) {
			// Om snittbetyget är samma sorteras de efter namn
			return name.compareTo(((Applicant) other).name);
		}
		return gradeRes;
	}


	// Skriver ut alla applikanter i vektorn man lägger in som parameter
	// Man behöver bara en vektor, den andra parametern är till för om man vill få ut andelen av
	// sökande som har bra nog snittbetyg
	// parametern mode bestämmer vilken utskrift man vill göra. Ska man ej få ut andelen kan man
	// använda samma vektor på de två första parametrarna
	public static void print(Applicant[] applicantsArray, Applicant[] applicantsArrayCompare, int mode) {

		// deklarerar storleken av vektorn som en variabel och en annan vektor beroende på vilken
		// utskrift man vill göra
		int size = applicantsArray.length;
		String[] printMode = { "applikanter lästes in korrekt:", "applikanter har högt nog medelbetyg:",
				"Andel av sökande med tillräckliga betyg: " };
		// Om mode = 2 delar den antalet godkända sökande med antalet totala sökande för att få ut
		// andelen
		if (mode == 2) {
			System.out.println(printMode[mode] + "cirka "
					+ Math.round((((double) applicantsArray.length / (double) applicantsArrayCompare.length) * 100))
					+ "%");
			// Om man istället vill skriva ut en hel vektor skrivs totala mängden objekt ut
			// Sen itererar den över hela vektorn och skriver ut objekten som en String
		} else {
			System.out.println(applicantsArray.length + " " + printMode[mode]);
			for (int j = 0; j < size; j++) {
				System.out.println("	" + applicantsArray[j].applicantToString());
			}
		}
		System.out.println();
	}
}
