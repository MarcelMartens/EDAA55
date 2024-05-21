import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accounts;
	
	/** Skapar en ny bank utan konton. */
	Bank() {
		accounts = new ArrayList<BankAccount>();
	}
	
	/**
	 * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare med de givna
	 * uppgifterna ska inte en ny Customer skapas, utan istället den befintliga användas. Det
	 * nya kontonumret returneras.
	 */
	int addAccount(String holderName, long idNr) {
		try {
			if (findHolder(idNr) != null) {
				accounts.add(new BankAccount(findHolder(idNr)));
			} else {
				System.out.println("Ingen befintligt kund hittades, ett ny skapas...");
				accounts.add(new BankAccount(holderName, idNr));
			}
			return accounts.get(accounts.size() - 1).getAccountNumber();
		} catch (Exception e) {
			System.out.println("Något gick fel med skapandet av kontot, försök igen!");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * Returnerar den kontoinnehavaren som har det givna id-numret, eller null om ingen sådan
	 * finns.
	 */
	Customer findHolder(long idNr) {
		try {
			
			for (BankAccount account : accounts) {
				if (account.getHolder().getIdNr() == idNr) {
					return account.getHolder();
				}
			}
			throw new Exception("NoIdFoundExeption");
		} catch (Exception e) {
			// System.out.println("Kunden med id " + idNr + " hittas ej i systemet, vänligen
			// försök igen!");
		}
		return null;
		
	}
	
	/**
	 * Tar bort konto med nummer 'number' från banken. Returnerar true om kontot fanns (och
	 * kunde tas bort), annars false.
	 */
	boolean removeAccount(int number) {
		try {
			if (findByNumber(number) != null) {
				return accounts.remove(findByNumber(number));
			}
			System.out.println("Kontonummer finns ej");
			return false;
		} catch (Exception e) {
			System.out.println("något gick fel, vänligen kontrollera kontonummer och försök igen");
			return false;
		}
		
	}
	
	/**
	 * Returnerar en lista innehållande samtliga bankkonton i banken. Listan är sorterad på
	 * kontoinnehavarnas namn.
	 */
	ArrayList<BankAccount> getAllAccounts() {
		if (accounts.isEmpty()) {
			return null;
		}
		ArrayList<BankAccount> sortedAccounts = new ArrayList<>();
		for (BankAccount account : accounts) {
			int index = 0;
			while (index < sortedAccounts.size() && sortedAccounts.get(index).getHolder().getName()
					.compareToIgnoreCase(account.getHolder().getName()) < 0) {
				index++;
			}
			sortedAccounts.add(index, account);
		}
		return sortedAccounts;
		
	}
	
	/**
	 * Söker upp och returnerar bankkontot med kontonummer 'accountNumber'. Returnerar null om
	 * inget sådant konto finns.
	 */
	BankAccount findByNumber(int accountNumber) {
		for (BankAccount account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
			
		}
		return null;
	}
	
	/**
	 * Söker upp alla bankkonton som innehas av kunden med id-nummer 'idNr'. Kontona
	 * returneras i en lista. Kunderna antas ha unika id-nummer.
	 */
	ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> holderAccounts = new ArrayList<>();
		for (BankAccount account : accounts) {
			if (account.getHolder() == findHolder(idNr)) {
				holderAccounts.add(account);
			}
		}
		return holderAccounts;
	}
	
	/**
	 * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla personer vars namn
	 * innehåller strängen 'namePart' inkluderas i resultatet, som returneras som en lista.
	 * Samma person kan förekomma flera gånger i resultatet. Sökningen är "case insensitive",
	 * det vill säga gör ingen skillnad på stora och små bokstäver.
	 */
	ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> customerList = new ArrayList<>();
		for (String string : nameList()) {
			if (string.toLowerCase().contains(namePart.toLowerCase())) {
				for (BankAccount account : accounts) {
					if (account.getHolder().getName() == string && !customerList.contains(account.getHolder())) {
						customerList.add(account.getHolder());
					}
				}
			}
		}
		return customerList;
	}
	
	private ArrayList<String> nameList() {
		ArrayList<String> nameList = new ArrayList<>();
		for (BankAccount account : accounts) {
			nameList.add(account.getHolder().getName());
		}
		return nameList;
	}
	
	/** returnar sista kontot som lades till */
	BankAccount getLast() {
		return accounts.get(accounts.size() - 1);
	}
	
	/** returnar kontot vid index */
	BankAccount getFromIndex(int index) {
		if (index <= accounts.size() && index >= 0) {
			return accounts.get(index);
		}
		return null;
	}
	
	/** hämtar index för ett account */
	int getIndex(BankAccount account) {
		return accounts.indexOf(account);
	}
	
}
