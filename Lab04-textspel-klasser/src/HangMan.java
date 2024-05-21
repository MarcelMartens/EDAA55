import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HangMan {
	private Graphics g = new Graphics();

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		HangMan man = new HangMan();
		while (true) {
			man.check();
			man.waitForSpace();

		}
	}

	public void waitForSpace() {
		int key = 0;
		while (key != 32) {
			key = g.waitForKey();
		}
		g.eraseAll();
	}

	public void check() throws FileNotFoundException, InterruptedException {

//		init listor och väljer ordet för denna rundan.
		char[] word = g.word();
//		char[] word = new char[] {'q','g','l','a'};
		char[] guess = new char[word.length];
		List<Character> listC = new ArrayList<Character>();
		List<Integer> indexList = new ArrayList<Integer>(word.length);
		List<Character> wrongList = new ArrayList<Character>();

//		gör om ordet från char-array till en lista
		for (int j = 0; j < word.length; j++) {
			listC.add(word[j]);
		}

//		skapar linjer för varje bokstav i det valda ordet och ställer in variabler
		g.LineGuesses(word.length);
		g.wrongBox();

//		huvudloopen för programmet. kör tills man har skrivit fel  11 gånger eller skrivit alla korrekta bokstäver
		for (int i = 1; i <= 11; i++) {

//			väntar tills användaren trycker på en tangent och kollar om bokstaven stämmer med någon av de i ordet
			char input = g.waitForKey();
			boolean contains = listC.contains(input);

//			hoppar över en runda ifall man trycker på en knapp som redan är testad


			if (wrongList.contains(input)) {
				g.writeText(600, 100, "Allready typed" + " " + input);
				g.EraseText(1000);
				i--;
				continue;
			}
			if (wrongList.contains(input) == false) {
				wrongList.add(input);
			}
			g.wrongBox();
			g.WrongBoxLetters(wrongList);

//			Koden som körs ifall man trycker fel bokstav. 
			if (contains == false) {
				g.wrong(i);
				if (i < 11) {
					g.writeText(600, 100, "Wrong letter, try again!");
					g.EraseText(1000);

//					körs ifall det var sista försöket
				} else if (i == 11) {
					g.writeText(500, 300, "Game Over!");
					g.writeText(400, 500, "the word was:" + " " + String.valueOf(word));
					g.writeText(400, 700, "press space to play again");

				}

//				koden som körs ifall man trycker rätt bokstav
			} else if (contains == true) {
				indexList.clear();
				i--;

//				kontrollerar för mer än en av samma bokstav i ordet och lägger till den i en lista
				for (int d = 0; d < word.length; d++) {
					if (input == listC.get(d)) {
						indexList.add(d);
					}
				}

//				lägger till bokstaven man tryckte på rätt plats i arrayn guess
				for (int k = 0; k < indexList.size(); k++) {
					guess[indexList.get(k)] = input;
					g.writeText(g.textStart(word.length) + (100 * indexList.get(k)) + 20, 895,
							String.valueOf(listC.get(indexList.get(k))));
				}

//				kollar ifall de bokstäverna du gissat stämmer överrens med ordet som valdes
				if ((Arrays.equals(guess, word)) == true) {
					g.writeText(500, 300, "congratulations! you won!");
					g.writeText(500, 500, "press space to play again");
					i = 13;
				} else {
					g.writeText(600, 100, "Correct letter!");
					g.EraseText(1000);
				}

			}
		}
	}
}
