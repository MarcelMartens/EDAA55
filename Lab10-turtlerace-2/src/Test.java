import java.util.Random;

public class Test {
	public static void main(String[] args) throws InterruptedException   {
		Random rand = new Random();
		while (true) {
		System.out.println(rand.nextInt(5)+1);
		Thread.sleep(200);
		}
		
	}

}
