import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeRunnerLAndR {
	Maze maze;
	Maze[] mazes;
	MazeWalker thomas1;
	MazeWalker thomas2;
	SimpleWindow window;
	int mazeNbr = 0;
	int count = 0;
	int delay = 10;
	int wAmount, tAmount;

	public static void main(String[] args) {
		MazeRunnerLAndR runner = new MazeRunnerLAndR();
//		huvudloop, loopar tills programmet stängs
		while (true) {
			runner.wAmount = runner.tAmount = 0;
			runner.constructField("t1","t2",5);
//			sätter längre delay mellan steg för längre labyrinter
			if (runner.mazeNbr >= 4) {
				runner.delay = 1;

			} else {
				runner.delay = 50;
			}
//			"spelloop" körs en gång per steg tills thomas är vid utgången
			while (runner.maze.atExit(runner.thomas1.getX(), runner.thomas1.getY()) == false) {
				SimpleWindow.delay(runner.delay);
//				kollar om thomas ser vägg till vänster och inte framför sig. Går framåt tills det ej stämmer
				while (runner.checkleft() == true && runner.checkForward() == false) {
					runner.thomas1.walk(1);
				}
//				kollar om while loopen avslutas pga vägg till vänster eller frammåt och svänger/går utifrån det
				if (runner.checkleft() == false) {
					runner.thomas1.turn(90);
					runner.thomas1.walk(1);

				} else if (runner.checkForward() == true) {
					runner.thomas1.turn(270);
					runner.thomas1.walk(1);
				}
			}
		}
	}

//	skapar ett simplewindow och en maze för thomas att springa. skapar bara nya objekt första gången spelet körs, sedan uppdaterar den objekten.
	private void constructField(int mNbr) {

		SimpleWindow w = new SimpleWindow(550, 550, "Mazerunner 2, electric bogaloo!");
		this.window = w;

		Maze m = new Maze(mNbr);
		this.maze = m;
		maze.draw(w);

		MazeWalker t1 = new MazeWalker(this.window, maze, maze.getXEntry(), maze.getYEntry());
		this.thomas1 = t1;
		MazeWalker t2 = new MazeWalker(this.window, maze, maze.getXEntry(), maze.getYEntry());
		this.thomas2 = t2;
		

	}

//	kollar om thomas har en vägg åt vänster
	private boolean checkleft() {
		return maze.wallAtLeft(thomas1.getDirection(), thomas1.getX(), thomas1.getY());
	}

//	kollar om thomas har en vägg framför sig
	private boolean checkForward() {
		return maze.wallInFront(thomas1.getDirection(), thomas1.getX(), thomas1.getY());
	}
	private boolean checkRight() {
		
	}
}
