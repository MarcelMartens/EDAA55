import java.util.Scanner;

public class SimpleWindowKeyValuesTest {

	public static void main(String[] args) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		while (true) {
			Thread.sleep(50);
			System.out.println("line");
			if (scan.hasNext()) {
				System.out.println(scan.next());
				if (scan.nextInt() == 1) {
					break;
				}
			}
		}
	}
}
