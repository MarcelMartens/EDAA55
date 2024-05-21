import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {
	private SimpleWindow w;

	public Graphics() {
		this.w = new SimpleWindow(1400, 1000, "Hänga Gubbe");
	}

//	Hämtar ett slumpmässigt ord från en textfil och gör om det till en array av bokstäver
	public char[] word() throws FileNotFoundException {
		char[] chars;
		while (true) {
			Scanner readWords = new Scanner(new File("words.txt"));
			ArrayList<String> wordList = new ArrayList<String>();
			while (readWords.hasNext()) {
				wordList.add(readWords.next());
			}
			int randLine = (int) (Math.random() * wordList.size());
			String randWord = wordList.get(randLine);
			chars = randWord.toCharArray();
			if (chars.length >= 11) {
				continue;
			}
			return chars;
		}
	}

//	ritar om man gissar fel
	public void wrong(int count) {
		if (count >= 6) {
			w.setLineWidth(5);
		} else {
			w.setLineWidth(10);
		}
		if (count == 1) {
//			bas
			w.moveTo(100, 550);
			w.lineTo(300, 550);
		} else if (count == 2) {
//			pinne uppåt
			w.moveTo(200, 550);
			w.lineTo(200, 100);
		} else if (count == 3) {
//			pinne längst upp
			w.moveTo(200, 100);
			w.lineTo(400, 100);
		} else if (count == 4) {
//			stabilitetspinne och snara
			w.moveTo(250, 100);
			w.lineTo(200, 150);
			w.moveTo(400, 100);
			w.setLineWidth(5);
			w.lineTo(400, 150);
		} else if (count == 5) {
//			huvud
			w.moveTo(400, 150);
			w.setLineWidth(5);
			w.lineTo(430, 150);
			w.lineTo(430, 210);
			w.lineTo(370, 210);
			w.lineTo(370, 150);
			w.lineTo(430, 150);
		} else if (count == 6) {
//			kropp
			w.setLineWidth(5);
			w.moveTo(400, 210);
			w.lineTo(400, 350);
		} else if (count == 7) {
//			arm1
			w.moveTo(440, 280);
			w.lineTo(400, 240);
		} else if (count == 8) {
//			arm2
			w.moveTo(400, 240);
			w.lineTo(360, 280);
		} else if (count == 9) {
//			ben1
			w.moveTo(440, 390);
			w.lineTo(400, 350);
		} else if (count == 10) {
//			ben2
			w.moveTo(400, 350);
			w.lineTo(360, 390);
		} else if (count == 11) {
			w.setLineWidth(2);
//			vänster kryssöga
			w.moveTo(420, 170);
			w.lineTo(410, 160);
			w.moveTo(420, 160);
			w.lineTo(410, 170);
//			höger kryss-öga
			w.moveTo(380, 170);
			w.lineTo(390, 160);
			w.moveTo(380, 160);
			w.lineTo(390, 170);

		}

	}

//	delegerar w.waitforkey så det kan användas i programmet
	public char waitForKey() {
		return w.waitForKey();
	}

//	skriver text vid (x,y) med texten txt
	public void writeText(int x, int y, String txt) {
		w.moveTo(x, y);
		w.writeText("");
		w.writeText(txt);
	}

//	tar bort text skriven av writeText efter "waitTime" millisekunder
	public void EraseText(int waitTime) throws InterruptedException {
		Thread.sleep(waitTime);
		w.setLineWidth(150);
		w.setLineColor(Color.white);
		w.moveTo(550, 100);
		w.lineTo(1400, 100);
		w.setLineColor(Color.black);
		w.setLineWidth(10);
	}

//	Ritar ut ett antal linjer centrerat i x-led beroende på hur långt ordet är
	public void LineGuesses(int wordSize) {
		w.getAdvancedControls().setFontSize(70);
		w.setLineWidth(5);
		int space = 30;
		int lineSize = 70;
		int xStart = 0;
		if (wordSize <= 8) {
			xStart = (1400 - ((30 * (wordSize - 1)) + (70 * wordSize))) / 2;
		} else if (wordSize > 8) {
			xStart = 330 + ((1070 - ((30 * (wordSize - 1)) + (70 * wordSize))) / 2);
		}
		int y = 900;
		w.moveTo(xStart, y);
		for (int a = 1; a <= wordSize; a++) {
			xStart += lineSize;
			w.lineTo(xStart, y);
			xStart += space;
			w.moveTo(xStart, y);
		}
	}

//	skickar tillbaka var första linjen är så att bokstäverna kan skrivas på rätt ställe
	public int textStart(int wordSize) {
		w.setLineWidth(5);
		int xStart = (1400 - ((30 * (wordSize - 1)) + (70 * wordSize))) / 2;
		int firstLetterX = xStart;
		return firstLetterX;
	}

//	ritar en låda för ord som redan använts
	public void wrongBox() {
		w.setLineWidth(200);
		w.setLineColor(Color.white);
		w.moveTo(175, 610);
		w.lineTo(175, 1000);
		w.setLineColor(Color.black);
		w.setLineWidth(5);
		w.moveTo(50, 600);
		w.lineTo(300, 600);
		w.lineTo(300, 950);
		w.lineTo(50, 950);
		w.lineTo(50, 600);
	}

//	ritar över hela rutan
	public void eraseAll() {
		w.setLineColor(Color.white);
		w.setLineWidth(1100);
		w.moveTo(0, 500);
		w.lineTo(1400, 500);
		w.setLineColor(Color.black);
	}

	public void WrongBoxLetters(List<Character> listName) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		int x = 50;
		int count = 0;
		int y = 650;
//		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (int t = 0; t <= 25; t++) {
			if (listName.contains(alphabet[t]) == true) {
//				indexList.add(listName.indexOf(alphabet[t]));
//				listName.remove(listName.indexOf(alphabet[t]));
//				listName.add(t, alphabet[t]);
//			}
//		}
//		System.out.println(indexList);
//				for (count = 0; count < indexList.size(); count++) {
				if (count >= 0 && count <= 3) {
//						for (int j = 1; j <= 4; j++) {
					w.moveTo(x, y);
					w.writeText(String.valueOf(listName.get(count)));
					x += 50;
				} else if (count >= 4 && count <= 7) {
//						for (int j = 1; j <= 4; j++) {
					if (x >= 250) {
						x = 50;
						y += 70;
					}
					w.moveTo(x, y);
					w.writeText(String.valueOf(listName.get(count)));
					x += 50;
				} else if (count >= 8 && count <= 11) {
					if (x >= 250) {
						x = 50;
						y += 70;
					}
					w.moveTo(x, y);
					w.writeText(String.valueOf(listName.get(count)));
					x += 50;
				} else if (count >= 12 && count <= 15) {
					if (x >= 250) {
						x = 50;
						y += 70;
					}
					w.moveTo(x, y);
					w.writeText(String.valueOf(listName.get(count)));
					x += 50;
				} else if (count >= 16 && count <= 19) {
					if (x >= 250) {
						x = 50;
						y += 70;
					}
					w.moveTo(x, y);
					w.writeText(String.valueOf(listName.get(count)));
					x += 50;
				} else if (count >= 21 && count <= 23) {

				}
				count++;
			}
		}
	}
}
