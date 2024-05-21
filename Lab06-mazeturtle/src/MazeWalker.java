import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeWalker {
	SimpleWindow window;
	Turtle turtle;
	Maze maze;
	
	public MazeWalker(SimpleWindow w, Maze m, int xStart, int yStart) {
		this.window = w;
		this.maze = m;
		Turtle t = new Turtle(w, xStart, yStart);
		this.turtle = t;
		this.turtle.penDown();
	}
	
	public void walk(int distance) {
		this.turtle.forward(distance);
	}
	public int getDirection() {
		return this.turtle.getDirection();
	}
	public int getX() {
		return this.turtle.getX();
	}
	public int getY() {
		return this.turtle.getY();
	}
	public void turn(int directionChange) {
		this.turtle.left(directionChange);
	}
	public void newPos(int newX, int newY) {
		this.turtle.jumpTo(newX, newY);
	}

}
