import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.window.Sprite;

public class Turtle {
	SimpleWindow window;
	boolean penState;
	double xPos, yPos, direction;
	Sprite tSprite = new Sprite("turtle.png", 5, 5);

	/**
	 * Skapar en sköldpadda som ritar i ritfönstret w. Från början befinner sig
	 * sköldpaddan i punkten x, y med pennan lyft och huvudet pekande rakt uppåt i
	 * fönstret (i negativ y-riktning).
	 */
	public Turtle(SimpleWindow w, int x, int y) {
		this.window = w;
		this.penUp();
		this.jumpTo(x, y);
		this.turnNorth();
		this.window.getAdvancedControls().addSprite(tSprite);

	}

	/** Sänker pennan. */
	public void penDown() {
		this.penState = true;
	}

	/** Lyfter pennan. */
	public void penUp() {
		this.penState = false;
	}

	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {
		this.jumpTo(this.xPos, this.yPos);
		double newX = this.xPos + (n * Math.cos(this.direction));
		double newY = this.yPos - (n * Math.sin(this.direction));
		if (this.penState == true) {
			this.window.lineTo((int) Math.round(newX), (int) Math.round(newY));
		}
		this.jumpTo(newX, newY);
	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		this.direction += beta * (Math.PI / 180);
		if (this.direction > 2 * Math.PI) {
			this.direction -= 2 * Math.PI;
		}

	}

	/**
	 * Går till punkten newX, newY utan att rita. Pennans läge (sänkt eller lyft)
	 * och huvudets riktning påverkas inte.
	 */
	public void jumpTo(double newX, double newY) {
		this.drawTurtle(newX, newY);
		this.xPos = newX;
		this.yPos = newY;
		this.window.moveTo((int) Math.round(newX), (int) Math.round(newY));

	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		this.direction = Math.PI / 2;

	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return (int) Math.round(this.xPos);
	}

	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return (int) Math.round(this.yPos);
	}

	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
	public int getDirection() {
		return (int) Math.round(this.direction / (Math.PI / 180));
	}

	private void drawTurtle(double newX, double newY) {
		double tempX = this.xPos;
		double tempY = this.yPos;
		for (int i = 0; i < 2; i++) {
			this.tSprite.moveMidTo((int) Math.round(tempX), (int) Math.round(tempY));
			tempX = newX;
			tempY = newY;
		}

	}
}
