import java.util.Random;

// ärva från klassen Turtle
// få startnummer
// gå random 1-6 steg varje runda
public class RaceTurtle extends Turtle {
	private int startNbr;
	private Random rand;

	public RaceTurtle(RaceWindow window, int startNbr) {
		super(window, RaceWindow.getStartXPos(startNbr), RaceWindow.getStartYPos(startNbr));
		this.rand = new Random();
		this.startNbr = startNbr;
		this.left(270);
		this.penDown();
	}
	public void raceStep() {
		this.forward(rand.nextInt(6) + 1);
	}
	public String toString() {
		return ("Nummer "+this.startNbr);
	}
}
