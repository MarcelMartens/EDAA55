package memory;

import javax.swing.JOptionPane;

public class MemoryGame {

	public static final String[] frontFileNames = { "can.jpg", "flopsy_mopsy_cottontail.jpg", "friends.jpg",
			"mother_ladybird.jpg", "mr_mcgregor.jpg", "mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };


	public static void main(String[] args) throws InterruptedException {
// init. värden och vektorer
		int r1, c1, tryCount;
		r1 = c1 = tryCount = 0;
		int size = 4;
		MemoryBoard board = new MemoryBoard(size, "back.jpg", frontFileNames);
		MemoryWindow window = new MemoryWindow(board);
//		huvudloop, körs om och om igen tills spelet stängs
		while (true) {
//			del-loop, körs tills spelet är vunnet
			while (!board.hasWon()) {
				window.drawBoard();
//				kollar var spelaren trycker och vänder + ritar det kortet
				for (int c = 0; c < 2; c++) {
					window.waitForMouseClick();
//					sparar positionen för det första kortet
					if (c == 0) {
						r1 = window.getMouseRow();
						c1 = window.getMouseCol();
					}
					board.turnCard(window.getMouseRow(), window.getMouseCol());
					window.drawCard(window.getMouseRow(), window.getMouseCol());
				}
//				väntar en halv sekund sen kollar om man tryckt på likadana kort. Om inte så vänder den tillbaka de.
				Thread.sleep(500);
				if (!board.same(r1, c1, window.getMouseRow(), window.getMouseCol())) {
					board.turnCard(window.getMouseRow(), window.getMouseCol());
					board.turnCard(r1, c1);
				}
				tryCount++;

			}
			JOptionPane.showMessageDialog(null, "You Win! It took you "+tryCount+" tries. To play again, press OK");
			board.resetBoard();
			window.clear();
			tryCount = 0;

		}
	}

}
