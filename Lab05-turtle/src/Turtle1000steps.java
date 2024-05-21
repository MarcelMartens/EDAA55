import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class Turtle1000steps {

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(800, 800, "dis a random turtle");
		Turtle t = new Turtle(w, 400, 400);
		Random rand = new Random();
		t.penDown();
		w.setLineWidth(2);
		for (int i = 0; i<1000; i++) {
			SimpleWindow.delay(5);
			t.forward(rand.nextInt(10)+1);
			t.left((rand.nextInt(360)-179));
		}
		

	}

}
