import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class TurtleRace {

	private static ArrayList<RaceTurtle> rArrayList = new ArrayList<RaceTurtle>();
	private static ArrayList<RaceTurtle> winArrayList = new ArrayList<RaceTurtle>();
	private static RaceWindow window = new RaceWindow();
	private static Random rand = new Random();


	public static void main(String[] args) {

		drawStart();
		createTurtles();
		while (rArrayList.size() > 5) {
			runRace();
			checkGoal();
		}
		drawWinners();
	}


	private static void createTurtles() {

		for (int c = 0; c < 8; c++) {
			switch (rand.nextInt(3)) {
			case 0:
				rArrayList.add(c, new MoleTurtle(window, c + 1));
				break;
			case 1:
				rArrayList.add(c, new DizzyTurtle(window, c + 1));
				break;
			case 2:
				rArrayList.add(c, new AbsentMindedTurtle(window, c + 1));
				break;
			default:
				break;
			}

		}

	}


	private static void runRace() {

		for (RaceTurtle rT : rArrayList) {
			rT.raceStep();
		}
	}


	private static void checkGoal() {

		for (RaceTurtle rT : rArrayList) {
			if (rT.getX() >= RaceWindow.X_END_POS) {
				winArrayList.add(rT);
			}
		}
		for (RaceTurtle wT : winArrayList) {
			rArrayList.remove(wT);
		}

	}


	private static void drawWinners() {

		int c = 0;
		String[] pallPlatser = { "#1:", "#2:", "#3:" };
		for (int y = 310; y <= 370; y += 30) {
			window.moveTo(10, y);
			window.writeText(pallPlatser[c]);
			window.moveTo(55, y);
			window.writeText(winArrayList.get(c).toString());
			c++;
		}
	}


	private static void drawStart() {

		window.moveTo(150, 90);
		window.writeText("Click window to start race");
		window.waitForMouseClick();
		window.moveTo(0, 85);
		window.setLineWidth(30);
		window.setLineColor(Color.WHITE);
		window.lineTo(800, 90);
		window.setLineColor(Color.BLACK);
		window.setLineWidth(1);
	}

}
