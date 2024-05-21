package memory;

import java.util.Random;

public class MemoryBoard {

	int size, nbrOfCards;
	Random rand;
	MemoryCardImage[][] cardPos;
	boolean[][] frontSideUp;

//Skapar ett board-objekt med tillhörande attribut
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {

		this.rand = new Random();
		this.size = size;
		this.nbrOfCards = size * size;
		this.cardPos = new MemoryCardImage[size][size];
		this.frontSideUp = new boolean[size][size];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				frontSideUp[r][c] = false;
			}
		}
		createCards(backFileName, frontFileNames);
	}


	/*
	 * Skapar size * size / 2 st memorykortbilder. Placerar ut varje kort på två slumpmässiga
	 * ställen på spelplanen.
	 */
	private void createCards(String backFileName, String[] frontFileNames) {

		int r, c, count;
		for (int i = 0; i < (nbrOfCards / 2); i++) {
			MemoryCardImage card = new MemoryCardImage(frontFileNames[i], backFileName);
			count = 0;
			while (true) {
				r = random(4);
				c = random(4);
				if (cardPos[r][c] == null) {
					cardPos[r][c] = card;
					count++;
					if (count == 2) {
						break;
					}
				}
			}
		}
	}


	public int random(int range) {

		return rand.nextInt(range);
	}


	/** Tar reda på brädets storlek. */
	public int getSize() {

		return this.size;
	}


	/**
	 * Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c. Raderna och kolonnerna numreras
	 * från 0 och uppåt.
	 */
	public MemoryCardImage getCard(int r, int c) {

		return cardPos[r][c];
	}


	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {

		frontSideUp[r][c] = !(frontUp(r, c));
	}


	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {

		return frontSideUp[r][c];
	}


	/**
	 * Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2, kolonn c2.
	 */
	public boolean same(int r1, int c1, int r2, int c2) {

		if (cardPos[r1][c1] == cardPos[r2][c2]) {
			return true;
		} else {
			return false;
		}
	}


	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (frontUp(r, c) == false) {
					return false;
				}
			}
		}
		return true;
	}


	public void resetBoard() {

		this.cardPos = new MemoryCardImage[size][size];
		createCards("back.jpg", MemoryGame.frontFileNames);
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				this.frontSideUp[r][c] = false;
			}
		}
	}
}
