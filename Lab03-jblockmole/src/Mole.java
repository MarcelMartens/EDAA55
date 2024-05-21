public class Mole {
    private Graphics g = new Graphics(50, 80, 10);

    public static void main(String[] args) {
	Mole mole = new Mole();
	mole.drawWorld();
	mole.dig();

    }

    public void drawWorld() {
	g.rectangle(0, 0, 50, 80, Colors.SKY);
	g.rectangle(0, 30, 50, 50, Colors.SOIL);
	g.rectangle(0, 29, 50, 1, Colors.GRASS);
	g.rectangle(0, 0, 7, 7, Colors.SUN);

    }

    public void dig() {
//	sätter startposition
	int x = g.getWidth() / 2;
	int y = (g.getHeight() + 40) / 2;
	int yLast = y;
	g.block(x, y, Colors.MOLE);
//	kollar ifall mulvaden är längs med kanterna och sätter tillbaka den i rutan
	while (true) {
	if (x >= g.getWidth()) {
	    x = g.getWidth() - 1;
	    g.block(x, y, Colors.MOLE);
	} else if (x < 0) {
	    x = 0;
	    g.block(x, y, Colors.MOLE);
	} else if (y >= g.getHeight()) {
	    y = g.getHeight() - 1;
	    g.block(x, y, Colors.MOLE);
	} else if (y < 28) {
	    y = 28;
	}
//	väntar på att man trycker på tangentbordet och fixar så att den bara gör tunnlar under marken
	char key = g.waitForKey();
	if (y >= 30) {
	    g.block(x, y, Colors.TUNNEL);
	} else if (y <= 28) {
	    g.block(x, y, Colors.SKY);
	}
//	läser av vilken tangent som trycktes och rör mulvaden åt det hållet
	if (key == 'w') {
	    y -= 1;
	    g.block(x, y, Colors.MOLE);
	} else if (key == 'd') {
	    x += 1;
	    g.block(x, y, Colors.MOLE);

	} else if (key == 's') {
	    y += 1;
	    g.block(x, y, Colors.MOLE);

	} else if (key == 'a') {
	    x -= 1;
	    g.block(x, y, Colors.MOLE);
	}
//	flyttar mulvaden ovanför gräset om den kommer underifrån och under gäset om den kommer ovanifrån
	if (y == 29 && yLast == 30) {
	    g.block(x, y, Colors.SOIL);
	    y = 28;
	    g.block(x, y, Colors.MOLE);
	} else if (y == 29 && yLast == 28) {
	    g.block(x, y, Colors.SOIL);
	    y = 30;
	    g.block(x, y, Colors.MOLE);
	} else if (y <= 27) {
	    g.block(x, y, Colors.SKY);
	    y = 28;
	    g.block(x, y, Colors.MOLE);
	}
//	sätter key till p så att märker att man trycker om man trycker samma tangent två gånger på raken. loggar också förra y-positionen
	key = 'p';
	yLast = y;
    }
    }

}
