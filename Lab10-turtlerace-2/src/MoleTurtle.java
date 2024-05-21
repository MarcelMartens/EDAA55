import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import se.lth.cs.pt.window.SimpleWindow;

public class MoleTurtle extends RaceTurtle {

	private SimpleWindow window;
	private Image holeImage;


	public MoleTurtle(RaceWindow window, int startNbr) {

		super(window, startNbr);
		this.window = window;
		try {
		this.holeImage = ImageIO.read(new File("MoleHole.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void raceStep() {

		if (rand.nextInt(3) == 0) {
			if (this.penState == true) {
				penUp();
			} else {
				penDown();
			}
			window.moveTo(getX()+2, getY()-2);
			window.getAdvancedControls().drawImage(holeImage);
			window.moveTo(getX()-2, getY()+2);

		}
		super.raceStep();

	}


	public String toString() {

		return (super.toString() + " - MoleTurtle");
	}
}
