import java.util.Scanner;

public class BankApplication {
	private static Scanner scan;
	private static Bank bank;
	private static final String[] userChoice = { " Välj ett alternativ nedan genom att skriva respektive nummer (1-9)",
			"1. Hitta konton för en viss kontoinnehavare", "2. Sök kontoinnehavare på (del av) namn",
			"3. Sätta in pengar", "4. Ta ut pengar", "5. Överföring mellan konton", "6. Skapa nytt konto",
			"7. Ta bort konto", "8. Skriv ut bankens alla konton", "9. Avsluta" };
	
	public static void main(String[] args) {
		boolean end = init();
		while (!end) {
			end = runApplication();
			
		}
	}
	
	/** returnar en string beroende på choice och lägger till varString på rätt ställe. används istället för att skriva hela stringen då många fraser återanvänds */
	private static String str(String choice, String varString) {
		String returnString, fixedString;
		switch (choice) {
		case "e":
			fixedString = "något gick fel med ";
			break;
		
		case "c":
			fixedString = "vänligen kontrollera ";
			break;
		
		case "op":
			fixedString = "ditt val ";
			break;
		
		case "in":
			fixedString = "Vänligen skriv ett ";
			break;
		
		case "res":
			fixedString = "Följande ";
			varString += " hittades:";
			break;
		
		case "is":
			fixedString = "Applikationen ";
			varString += "...";
			break;
		
		case "bal":
			fixedString = "Ny balans på kontot: ";
			break;
		
		case "cant":
			fixedString = "Den valda summan går ej att ";
			break;
		
		case "pls":
			fixedString = "vänligen välj lämpligare ";
			break;
		
		case "no":
			fixedString = "Inga ";
			varString += " hittades.";
			break;
		
		default:
			returnString = choice + varString;
			fixedString = "";
			break;
		
		}
		returnString = fixedString + varString;
		return returnString + "\n";
	}
	
	/** ovverride för "syso" */
	private static void print(String s) {
		System.out.println(s);
	}
	
	/** ovverride för "syso" */
	private static void print(int i) {
		System.out.println(i);
	}
	
	/** ovverride för "syso" */
	private static void print(double d) {
		System.out.println(d);
	}
	
	/** ovverride för "syso" */
	private static void printany(Object obj) {
		System.out.println(String.class.cast(obj));
	}
	
	/** initierar bank och scan + andra saker som används */
	private static boolean init() {
		print("initializing application...");
		scan = new Scanner(System.in);
		bank = new Bank();
		bank.addAccount("Anna B", 1234);
		bank.addAccount("Anna B", 5678);
		bank.addAccount("Anna B", 5678);
		bank.addAccount("Jesper Z", 1313);
		bank.addAccount("Anders A", 1212);
		return false;
	}
	
	/** kör och loopar applikationen */
	private static boolean runApplication() {
		print("Application is now running...");
		boolean end = false;
		while (!end) {
			end = chooseOption();
			
		}
		return end;
		
	}
	
	/** returnar inputstreamen som datatypen type */
	private static <T> T next(Class<T> type) {
		String choice = scan.next() + scan.nextLine();
		try {
			if (type == Integer.class) {
				return type.cast(Integer.parseInt(choice));
				
			} else if (type == Double.class) {
				return type.cast(Double.parseDouble(choice));
				
			} else if (type == Long.class) {
				return type.cast(Long.parseLong(choice));
				
			} else if (type == String.class) {
				return type.cast(choice);
				
			}
			
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/** returnar index i arraylisten för kontonummer = nästa int i inputsteamen */
	private static int setIndex() {
		int ind = next(Integer.class);
		return bank.getIndex(bank.findByNumber(ind));
	}
	
	/** returnar BankAccount-objektet vid index */
	private static BankAccount accountAtIndex(int index) {
		if (bank.getFromIndex(index) != null) {
			return bank.getFromIndex(index);
			
		}
		return null;
	}
	
	/** väntr tills inputstream inte är tom */
	private static void waitNext() {
		while (!scan.hasNext()) {
			try {
				Thread.sleep(50);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
	}
	
	/** hjälpmetod för att skriva ut bankkonton och kollar så account != null */
	private static String toString(BankAccount account) {
		String s = "";
		if (account != null) {
			return s = account.toString();
			
		}
		return s;
	}
	
	/** huvudmetoden, switch-loop med varje val som användaren kan göra */
	private static boolean chooseOption() {
		
		boolean correctInput, endApplication;
		correctInput = endApplication = false;
		while (!correctInput) {
			correctInput = true;
			int id, index;
			id = index = 0;
			long idNr = 0;
			double bal = 0;
			String n = "";
			try {
				print("\n");
				for (String choices : userChoice) {
					print(choices);
					
				}
				switch (next(Integer.class)) {
				case 1:
					print(str("op", userChoice[1]) + str("in", "personnummer"));
					// kolla om det ej finns några konton
					if (bank.findAccountsForHolder(idNr = next(Long.class)).isEmpty()) {
						print(str("no", "kunder"));
						break;
						
					}
					for (BankAccount account : bank.findAccountsForHolder(idNr)) {
						print(str("res", "konton") + toString(account));
						
					}
					break;
				
				case 2:
					print(str("op", userChoice[2]) + str("in", "namn eller del av ett namn"));
					waitNext();
					// kolla om det ej finns några kunder
					if (bank.findByPartofName(n = next(String.class)).isEmpty()) {
						print(str("no", "kunder"));
						break;
						
					}
					print(str("res", "kunderna"));
					for (Customer customer : bank.findByPartofName(n)) {
						print(customer.toString());
						
					}
					break;
				
				case 3:
					print(str("op", userChoice[3]) + str("in", "kontonummer"));
					// kolla om det ej finns några konton
					if (accountAtIndex(index = setIndex()) == null) {
						print(str("no", "konton med det numret"));
						break;
						
					}
					print(str("op", toString(accountAtIndex(index))) + str("in", "insättningssumma"));
					// kolla om det går att utföra
					if ((bal = next(Double.class)) > 0) {
						accountAtIndex(index).deposit(bal);
						print(str("bal", accountAtIndex(index).toString()));
						break;
						
					}
					print(str("cant", "sätta in på kontot, ") + str("pls", "summa"));
					break;
				
				case 4:
					print(str("op", userChoice[4]) + str("in", "kontonummer"));
					// kolla om det ej finns några konton
					if (accountAtIndex(index = setIndex()) == null) {
						print(str("no", "konton med det numret"));
						break;
						
					}
					print(str("op", toString(accountAtIndex(index))) + str("in", "uttagssumma"));
					// kolla om det går att utföra
					if (accountAtIndex(index).getAmount() >= (bal = next(Double.class)) && bal > 0) {
						accountAtIndex(index).withdraw(bal);
						print(str("bal", toString(accountAtIndex(index))));
						break;
						
					}
					print(str("cant", "ta ut från kontot, ") + str("pls", "summa."));
					break;
				
				case 5:
					int tempIndex = 0;
					print(str("op", userChoice[5]) + str("in", "konto(nummer) att överföra till"));
					// kolla om det ej finns några konton
					if (accountAtIndex(index = setIndex()) == null) {
						print(str("no", "konton med det numret"));
						break;
						
					}
					print(str("op", "") + "överför till: " + toString(accountAtIndex(index)));
					print(str("in", "konto(nummer) att överföra från"));
					// kolla om det ej finns några konton
					if (accountAtIndex(tempIndex = setIndex()) == null) {
						print(str("no", "konton med det numret"));
						break;
						
					}
					print(str("op", "") + "överför från: " + toString(accountAtIndex(tempIndex)));
					print(str("in", "summan att överföra"));
					// kolla om det går att utföra
					if ((bal = next(Double.class)) > 0 && bal <= accountAtIndex(tempIndex).getAmount()) {
						accountAtIndex(index).deposit(bal);
						accountAtIndex(tempIndex).withdraw(bal);
						print(str("bal", toString(accountAtIndex(index))) + toString(accountAtIndex(tempIndex)));
						break;
						
					}
					print(str("cant", "föra över från kontot") + str("pls", "summa"));
					break;
				
				case 6:
					print(str("op", userChoice[6]) + str("in", "namn"));
					String name = next(String.class);
					print(str("in", "personnummer"));
					idNr = next(Long.class);
					print("konto med kontonummer " + bank.addAccount(name, idNr) + " öppnades");
					
					break;
				
				case 7:
					print(str("op", userChoice[7]) + str("in", "kontonummer"));
					id = next(Integer.class);
					// kolla om det utfördes
					if (bank.removeAccount(id) == true) {
						print("konto med kontonummer " + id + " raderades");
						
					}
					break;
				
				case 8:
					print(str("op", userChoice[8]));
					// kolla om det finns konton
					if (bank.getAllAccounts() == null) {
						print("Banken saknar konton");
						break;
						
					}
					for (BankAccount account : bank.getAllAccounts()) {
						id++;
						print(id + ":\n" + account.toString());
						
					}
					break;
				
				case 9:
					System.out.println("ditt val:  9. Avsluta \n Avslutar applikation...");
					endApplication = true;
					break;
				
				default:
					correctInput = false;
					print(str("e", "inmatningen") + str("c", "så du skrev rätt"));
					break;
				
				}
				return endApplication;
				
			} catch (Exception e) {
				print(str("e", "inläsningen") + str("c", "så du skrev rätt"));
				return endApplication;
				
			}
			
		}
		return false;
	}
}
