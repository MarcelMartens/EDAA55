import java.util.ArrayList;

public class TurtleRace {

	private static ArrayList<RaceTurtle> rArrayList = new ArrayList<RaceTurtle>();
	private static ArrayList<RaceTurtle> winArrayList = new ArrayList<RaceTurtle>();
	private static final RaceWindow window = new RaceWindow();


	public static void main(String[] args) {

		drawStart();
		createTurtles();
		while (!rArrayList.isEmpty()) {
			runRace();
			checkGoal();
		}
		drawWinners();

	}


	private static void createTurtles() {

		for (int c = 0; c < 8; c++) {
			rArrayList.add(c, new RaceTurtle(window, c + 1));
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
			window.moveTo(230, y);
			window.writeText(pallPlatser[c]);
			window.moveTo(300, y);
			window.writeText(winArrayList.get(c).toString());
			c++;
		}
	}


	private static void drawStart() {

		window.getAdvancedControls().setFontSize(30);
		window.moveTo(150, 90);
		window.writeText("Click window to start race");
		window.waitForMouseClick();
	}
}
