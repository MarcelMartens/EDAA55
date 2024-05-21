package rekrytering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

	private final static String[] numGrades = { "A", "B", "C", "F" };
	private static int c, i;


	public static Applicant[] readFromFile(String fileName, int nbrOfRows) {

		// Initisiera variabler och vektorer för inläsning
		FileReader.c = FileReader.i = 0;
		Scanner scan;
		int rowCount, errorCount, applicantCount;
		String name, grade;
		rowCount = errorCount = applicantCount = 0;
		// Hjälpvektorer för att bära över information mellan metoder och
		// använda utan att ändra på orginalet
		String[] nameAndGrades = new String[3];
		String[] grades = new String[5];
		// Skapar två temporära vektorer med hög kapacitet för att inte av
		// misstag fylla upp
		Applicant[] applicantsTemp = new Applicant[nbrOfRows];
		int[] errorRowsTemp = new int[nbrOfRows];
		// försöker skapa en scanner och ladda in en fil i minnet, ger
		// FileNotFoundExeption ifall filnamnet är fel
		try {
			scan = new Scanner(new File(fileName), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}
		// Mainloop för att läsa in alla applikanter rad för rad.
		// Läser nästa rad tills den antingen läst in lika många rader som
		// nbrOfRows eller om nästa rad inte finns
		while (rowCount <= nbrOfRows) {
			if (scan.hasNextLine() == false) {
				break;
			}
			// Försöker läsa in sökande på nuvarande rad och skapa ett
			// applicant-objekt
			// Om det misslyckas ger den ett exeption och lägger till
			// felaktiga raden i en array för manuell inmatning
			try {
				nameAndGrades = scan.nextLine().split(" ");
				name = nameAndGrades[0] + " " + nameAndGrades[1];
				grade = nameAndGrades[2];
				// Lägger till alla enskillda tecken i betyget till en ny
				// vektor för att möjliggöra ändring mellan bokstävsbetyg
				// och sifferbetyg
				grades = grade.split(",");
				// Kollar om betyget är på korrekt och om det, utan att
				// ändra, går att direkt skapa ett Applicant-objekt
				if (gradeChecker(grade)) {
					applicantsTemp[applicantCount] = new Applicant(name, grade);
					applicantCount++;
					// Om betyget ej går att direkt använda i
					// Applicant-konstruktorn kör den "parseInputgrade" för
					// att ändra från bokstäver till siffror
				} else {
					grade = parseInputGrade(grades);
					// Kollar ifall parseInputGrade har ändrat något
					// bokstäver i betyget
					// har den ändrat något antas det att det nu är korrekt
					// och används för att skapa en Applicant
					if (FileReader.c != 0 && FileReader.i == 4) {
						applicantsTemp[applicantCount] = new Applicant(name, grade);
						applicantCount++;
						// Om sökande går igenom hela try-blocket utan att
						// bli inläst korrekt kastas ett exeption för att
						// komma till catch.
					} else {
						throw new Exception();
					}
				}
				// Om sökande har felaktig inmatning läggs raden de är på
				// till i en Vektor för manuell inmatning senare.
			} catch (Exception e) {
				errorRowsTemp[errorCount] = rowCount + 1;
				errorCount++;
			}
			// errorCount och rowCount är variabler för att veta hur många
			// felaktiga respektive korrekta inläsningar som har skett
			// behövs för att elementen ska hamna på rätt plats i
			// slutgiltiga vektorn och minska tomma platser.
			rowCount++;
		}
		// Skriver ut totalt antal inlästa rader från dokumenter och
		// skriver ut de rader som blev fel.
		System.out.println("Totalt lästes " + rowCount + " rader in");
		System.out.println("Sökande på följande rader kunde ej läsas in och bör göras manuellt:");
		// Skapar en ny Vektor för felrader och sökande med exakt storlek.
		// Returnar enbart vektorn med sökande och skriver ut raderna till
		// konsollen.
		int[] errorRows = Arrays.copyOfRange(errorRowsTemp, 0, errorCount);
		System.out.println(Arrays.toString(errorRows));
		System.out.println();
		// skickar tillbaka vektorn applicants till metoden som kallade
		// readFromFile
		Applicant[] applicants = Arrays.copyOfRange(applicantsTemp, 0, applicantCount);
		return applicants;
	}


	// Metod för att söka igenom input-betyget och byta ut bokstävsbetyg
	// mot siffror + U
	// Deklarerar variabler för att kunna verifiera vad som ändrades
	// utanför metoden.
	private static String parseInputGrade(String[] wrongGrades) {

		i = c = 0;
		boolean repeat;
		// Loopar igenom och kollar ifall bokstäverna i numGrades (A,B,C,F)
		// finns i betyget
		for (String gr : numGrades) {
			i++;
			repeat = true;
			while (repeat) {
				// Kollar om bokstaven finns i betyget kör koden i
				// respektive case
				if (Arrays.asList(wrongGrades).contains(gr)) {
					switch (gr) {
					case "A":
						wrongGrades[Arrays.asList(wrongGrades).indexOf(gr)] = "5";
						c++;
						break;
					case "B":
						wrongGrades[Arrays.asList(wrongGrades).indexOf(gr)] = "4";
						c++;
						break;
					case "C":
						wrongGrades[Arrays.asList(wrongGrades).indexOf(gr)] = "3";
						c++;
						break;
					case "F":
						wrongGrades[Arrays.asList(wrongGrades).indexOf(gr)] = "U";
						c++;
						break;
					default:
						break;
					}
				}
				// Kollar om betyget fortfarande innehåller bokstaven som
				// nyss byttes ut och låter While-loopen gå tills alla av
				// den bokstaven är utbytta
				// Ifall den inte längre hittar bokstaven i betyget
				// avslutas while-loopen och foreach-loopen går till nästa
				// bokstav
				if (!Arrays.asList(wrongGrades).contains(gr)) {
					repeat = false;
				}
			}
		}
		// returnar det nya betyget som en string ett tecken i taget.
		// Skrivs på korrekt form
		return (wrongGrades[0] + "," + wrongGrades[1] + "," + wrongGrades[2] + "," + wrongGrades[3] + ","
				+ wrongGrades[4]);
	}


	// Metod för att kolla om betyget är på korrekt form och kan användas
	// direkt
	private static boolean gradeChecker(String grades) {

		boolean check = false;
		// Om betyget innehåller bokstäver returnar den false
		for (String n : numGrades) {
			if (Arrays.asList(grades.split(",")).contains(n)) {
				return check;
			}
		}
		// Enbart om snittbetyget är större eller lika med noll, mindre
		// eller lika med fem och innehåller totalt 5 enskillda betyg
		// returnas true
		Applicant applicantTemp = new Applicant("null", grades);
		if (applicantTemp.getAvgGrade() >= 0 && applicantTemp.getAvgGrade() <= 5
				&& applicantTemp.getGrades().length == 5) {
			check = true;
			// Om inga bokstäver hittas och betyget inte ses som korrekt
			// returnas false
		} else {
			check = false;
		}
		return check;
	}
}
