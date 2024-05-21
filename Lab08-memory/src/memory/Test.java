package memory;

import java.util.Random;

public class Test {

public static void main(String[] args) throws InterruptedException {
	Random random = new Random();
	int count = 0;
	while (count<100) {
	System.out.println(random.nextInt());
	Thread.sleep(50);
	count++;
	}
}
}
