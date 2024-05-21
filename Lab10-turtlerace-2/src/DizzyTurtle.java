

public class DizzyTurtle extends RaceTurtle{
	private int dizzyNess;
	
	public DizzyTurtle(RaceWindow window, int startNbr) {
		super(window, startNbr);
		this.dizzyNess = rand.nextInt(5)+1;
	}
	
	public void raceStep() {
		super.left(dizzyNess*randomTurn());
		super.raceStep();
	}
	public String toString() {
		return (super.toString()+" - DizzyTurtle (Yrsel: "+dizzyNess+")");
	}
	private int randomTurn() {
		int turn = rand.nextInt(3)-1;
		while (turn == 0) {
			turn = rand.nextInt(3)-1;
		}
		return turn;
	}
}
