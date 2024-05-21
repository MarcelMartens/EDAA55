package rekrytering;

import java.util.Arrays;

public class FindBestCandidates {

	// Variabler för att bestämma fil och minsta accepterade snittbetyg
	private static final double MIN_AVG_GRADE = 4;
	private static final String doc = "all";


	// Main-metoden för programmet
	public static void main(String[] args) {

		// Skapar en ny vektor med alla sökande som hade korrekt format på betyget/namnet
		// skapar en ny vektor för alla sökande med snittbetyg >= MIN_AVG_GRADE
		Applicant[] applicants = FileReader.readFromFile("applications_" + doc + ".txt", 500);
		Applicant[] bestApplicants = findBestCandidates(applicants);
		// Sorterar båda vektorerna utifrån snittbetyg i sjunkande ordning
		Arrays.sort(applicants);
		Arrays.sort(bestApplicants);
		// Skriver ut info till konsollen
		// antalet totalt korrekt inlästa sökande
		Applicant.print(applicants, applicants, 0);
		// antalet sökande med tillräckligt högt snittbetyg
		Applicant.print(bestApplicants, applicants, 2);
		// andelen av sökande med tillräckligt högt snittbetyg
		Applicant.print(bestApplicants, bestApplicants, 1);
	}


	// metod för att sålla ut alla sökande med för lågt betyg
	public static Applicant[] findBestCandidates(Applicant[] applicants) {

		// deklarerar en variabel för antalet sökande som har högt nog betyg och en vektor för alla
		// sökande som klarar sig
		// Ökar med 1 för varje gång en sökande läggs till
		int c = 0;
		Applicant[] bestApplicant = new Applicant[applicants.length];
		// itererar igenom hela vektorn av sökande med korrekta betyg och lägger till de i nya ifall
		// deras smittbetyg är högt nog
		for (Applicant applicant : applicants) {
			if (applicant.getAvgGrade() >= MIN_AVG_GRADE) {
				bestApplicant[c] = applicant;
				c++;
			}
		}
		// Returnar en ny vektor med exakt storlek för antalet som lades till
		return Arrays.copyOfRange(bestApplicant, 0, c);
	}
}
