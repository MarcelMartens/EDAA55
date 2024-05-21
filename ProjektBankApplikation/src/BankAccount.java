
public class BankAccount {
	private double amount;
	private Customer holder;
	private int kontoNr;
	private static int counter = 1000;

	/**
	 * Skapar ett nytt bankkonto åt en innehavare med namn 'holderName' och id
	 * 'holderId'. Kontot tilldelas ett unikt kontonummer och innehåller
	 * inledningsvis 0 kr.
	 */
	BankAccount(String holderName, long holderId) {
		this.holder = new Customer(holderName, holderId);
		this.amount = 0;
		this.kontoNr = counter;
		counter++;
	}

	/**
	 * Skapar ett nytt bankkonto med innehavare 'holder'. Kontot tilldelas ett unikt
	 * kontonummer och innehåller inledningsvis 0 kr.
	 */
	BankAccount(Customer holder) {
		this.holder = holder;
		this.amount = 0;
		this.kontoNr = counter;
		counter++;
	}

	/** Tar reda på kontots innehavare. */
	Customer getHolder() {
		return this.holder;

	}

	/** Tar reda på det kontonummer som identifierar detta konto. */
	int getAccountNumber() {
		return this.kontoNr;
	}

	/** Tar reda på hur mycket pengar som finns på kontot. */
	double getAmount() {
		return this.amount;
	}

	/** Sätter in beloppet 'amount' på kontot. */
	void deposit(double amount) {
		if (amount > 0) {
			this.amount += amount;
		}
	}

	/**
	 * Tar ut beloppet 'amount' från kontot. Om kontot saknar täckning blir saldot
	 * negativt.
	 */
	void withdraw(double amount) {
		if (this.amount >= amount) {
			this.amount -= amount;
		}
	}

	/** Returnerar en strängrepresentation av bankkontot. */
	public String toString() {
			String toString = "Kund   -   " + this.holder.toString() + "\n" + "konto   -   Kontonummer: " + this.kontoNr
					+ ", balans: " + this.amount + "kr" + "\n";
			return toString;
	}

}
