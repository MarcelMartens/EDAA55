import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class TurtleStopClose {

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(800, 800, "dis a random turtle");
		Turtle t = new Turtle(w, 250, 250);
		Turtle r = new Turtle(w, 350, 350);
		Random rand = new Random();
		t.penDown();
		r.penDown();
		w.setLineWidth(2);
		while (Math.abs(t.getY() - r.getY()) > 25 || Math.abs(t.getX() - r.getX()) > 25) {
			SimpleWindow.delay(5);
			t.forward(rand.nextInt(30) + 1);
			t.left((rand.nextInt(360) - 179));
			r.forward(rand.nextInt(30) + 1);
			r.left((rand.nextInt(360) - 179));
			if (t.getX()>=800) {
				t.jumpTo(800, t.getY());
			}else if (t.getX()<=0) {
				t.jumpTo(0, t.getY());
			}if (t.getY()>=800) {
				t.jumpTo(t.getX(), 800);
			}else if (t.getY()<=0) {
				t.jumpTo(t.getX(), 0);
			}
			if (r.getX()>=800) {
				r.jumpTo(800, r.getY());
			}else if (r.getX()<=0) {
				r.jumpTo(0, r.getY());
			}if (r.getY()>=800) {
				r.jumpTo(r.getX(), 800);
			}else if (r.getY()<=0) {
				r.jumpTo(r.getX(), 0);
			}
		}
	}
}
