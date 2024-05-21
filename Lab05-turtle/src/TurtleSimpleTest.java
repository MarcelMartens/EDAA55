import se.lth.cs.pt.window.SimpleWindow;

public class TurtleSimpleTest {

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "simple test");
		Turtle t = new Turtle(w, 250, 250);
		t.penDown();
		t.forward(50);
		t.left(90);
		t.forward(100);
		t.left(90);
		t.forward(20);
		t.left(90);
		t.forward(150);
		t.left(270);
		t.forward(60);
	}

}
