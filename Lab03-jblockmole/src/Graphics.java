
import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {
	// lägger till atribut till klassen Graphics
	private int width;
	private int height;
	private int blockSize;
	private SimpleWindow w;

	// skapar konstruktor
	public Graphics(int w, int h, int bs) {
		// kopplar samman klass-attributen med konstruktorns parametrar
		this.width = w;
		this.height = h;
		this.blockSize = bs;
		this.w = new SimpleWindow(width * blockSize, height * blockSize, "diggin");
	}

	public void square() {
		w.moveTo(10, 10);
		w.lineTo(10, 20);
		w.lineTo(20, 20);
		w.lineTo(20, 10);
		w.lineTo(10, 10);
	}

	// ritar block i fönstret vid kordinaterna x och y
	public void block(int x, int y, java.awt.Color Color) {
		w.setLineColor(Color);
		int left = x * blockSize;
		int right = left + blockSize - 1;
		int top = y * blockSize;
		int bottom = top + blockSize - 1;
		for (int row = top; row <= bottom; row++) {
			// ritar linjer fram och tillbaka för att skapa ifylld fyrkant. ritar "blockSize
			// - 1" gånger. börjar från toppen (kordinat y*blockSize) och går neråt
			w.moveTo(left, row);
			w.lineTo(right, row);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void rectangle(int x, int y, int width, int height, java.awt.Color c) {
//		ritar en rektangel av metoden block(). börjar i övre vänstra hörnet med kordinaten (x,y) och fortsätter till yy=y+height resp. xx=x+width. ritar "height * width" antal block.
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				block(xx, yy, c);
			}
		}
	}

	public char waitForKey() {
		return w.waitForKey();
	}
}
