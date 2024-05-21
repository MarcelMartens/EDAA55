

public class AbsentMindedTurtle extends RaceTurtle {
	private int absentMindedNess;
	
public AbsentMindedTurtle(RaceWindow window, int startNbr) {
	super(window, startNbr);
	this.absentMindedNess = rand.nextInt(101);
	}

	public void raceStep() {
		if (absentMindedNess >= rand.nextInt(101)) {
		}else {
			super.raceStep();
		}
	}
	public String toString() {
		return (super.toString()+" - AbsentMindedTurtle ("+absentMindedNess+"% frånvarande)");
	}
}
