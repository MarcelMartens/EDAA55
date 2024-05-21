import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeRunner {
	Maze maze;
	Maze[] mazes;
	MazeWalker thomas;
	SimpleWindow window;
	int mazeNbr = 0;
	int count = 0;
	int delay = 10;
	int wAmount, tAmount;

	public static void main(String[] args) {
		MazeRunner runner = new MazeRunner();
//		huvudloop, loopar tills programmet stängs
		while (true) {
			runner.wAmount = runner.tAmount = 0;
			runner.constructField();
//			sätter längre delay mellan steg för längre labyrinter
			if (runner.mazeNbr >= 4) {
				runner.delay = 1;
				
			} else {
				runner.delay = 50;
			}
//			"spelloop" körs en gång per steg tills thomas är vid utgången
			while (runner.maze.atExit(runner.thomas.getX(), runner.thomas.getY()) == false) {
				SimpleWindow.delay(runner.delay);
//				kollar om thomas ser vägg till vänster och inte framför sig. Går framåt tills det ej stämmer
				while (runner.checkleft() == true && runner.checkForward() == false) {
					runner.thomas.walk(1);
					runner.wAmount++;
				}
//				kollar om while loopen avslutas pga vägg till vänster eller frammåt och svänger/går utifrån det
				if (runner.checkleft() == false) {
					runner.thomas.turn(90);
					runner.tAmount++;
					runner.thomas.walk(1);
					runner.wAmount++;
					
				} else if (runner.checkForward() == true) {
					runner.thomas.turn(270);
					runner.tAmount++;
					runner.thomas.walk(1);
					runner.wAmount++;
				}
			}
//			skriver ut info om antal steg och ger info om hur man startar om
			runner.window.moveTo(100, 450);
			runner.window.writeText("Thomas has reached the end of the maze!");
			runner.window.moveTo(100, 530);
			runner.window.writeText("Press space to run another maze");
			runner.window.moveTo(100, 475);
			runner.window.writeText("He took "+runner.wAmount+" steps!");
			runner.window.moveTo(100, 500);
			runner.window.writeText("and turned "+runner.tAmount+" times!");
//			räknar antalet körda spel
			runner.count++;
			int intKey = 0;
//			väntar tills användare trycker på space för att starta om
			while (true) {
				intKey = runner.window.waitForKey();
				
				if (intKey == 32) {
					break;
				}
			}
		}
	}
//	skapar ett simplewindow och en maze för thomas att springa. skapar bara nya objekt första gången spelet körs, sedan uppdaterar den objekten.
	private void constructField() {

		if (count == 0) {
			SimpleWindow w = new SimpleWindow(550, 550, "Mazerunner 2, electric bogaloo!");
			this.window = w;
		}
		chooseMaze();

		if (count == 0) {
			Maze[] m = new Maze[6];
			for (int i = 0; i < 6; i++) {

				m[i] = new Maze(i + 1);
			}

			this.mazes = m;
		}

		this.maze = mazes[mazeNbr];
		maze.draw(this.window);

		if (count == 0) {
			MazeWalker t = new MazeWalker(this.window, maze, maze.getXEntry(), maze.getYEntry());
			this.thomas = t;

		} else if (count >= 1) {
			thomas.newPos(maze.getXEntry()-1, maze.getYEntry());
		}
	}
//	kollar om thomas har en vägg åt vänster
	private boolean checkleft() {
		return maze.wallAtLeft(thomas.getDirection(), thomas.getX(), thomas.getY());
	}
//	kollar om thomas har en vägg framför sig
	private boolean checkForward() {
		return maze.wallInFront(thomas.getDirection(), thomas.getX(), thomas.getY());
	}
//	låter användaren välja vilken labyrint thomas ska springa och återställer fönstret från föregående runda
	private void chooseMaze() {

		char[] validChoice = { '1', '2', '3', '4', '5', '6' };
		char keyPress;
		int c = 0;
		window.clear();
		window.moveTo(100, 480);
		window.writeText("press a number 1-6 to choose maze");

		while (c == 0) {
			keyPress = window.waitForKey();

			for (char i : validChoice) {

				if (keyPress == i) {
					c = keyPress - 48;
					mazeNbr = c-1;
					window.clear();

				} else {
					window.moveTo(100, 500);
					window.writeText("Not a valid input, try again");

				}
			}
		}
		window.clear();
	}
}
