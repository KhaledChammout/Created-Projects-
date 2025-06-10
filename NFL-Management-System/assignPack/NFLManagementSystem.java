package assignPack;
import java.util.Scanner;

public class NFLManagementSystem {
	 TeamManager team = new TeamManager();
	 PlayerManager player = new PlayerManager();
	 GameManager game = new GameManager();
	 Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws NFLException {
		NFLManagementSystem manager = new NFLManagementSystem();
			manager.load();
			manager.manageNFLE();
			System.out.println("A UUID (Universally Unique Identifier) is a 128-bit value used to uniquely identify information."
+ " When a class implements Serializable, it's recommended to declare a serialVersionUID to ensure version compatibility when objects are serialized");
	}
	public void load() throws NFLException {
		 team.loadTeamsFromFile("C:\\labFiles\\teams.csv");
	     player.loadPlayersFromFile("C:\\labFiles\\players.csv", team);
	     game.loadGamesFromFile("C:\\labFiles\\games.csv");
	}
	public void manageNFLE() {
		int option;
		do {
			showMenu();
			System.out.println("Enter your choice: ");
			option = getUserOption();
			try {
				switch(option) {
				case 1: 
					team.displayTeams();		
					break;
				case 2:
					player.displayPlayers();
					break;
				case 3:
					game.displayGames();
					break;
				case 4:
					createTeam(scanner, team);
					break;
				case 5:
					addPlayerToTeam(scanner, player, team);
					break;
				case 6:
					findTeam(scanner, team);
					break;
				case 7:
					exitProgram();
					break;
				default:
					System.out.println("BookException: Invalid entry: enter an integer between 1 and 8");
				}
			} catch (NFLException e) {
				System.err.println(e.getMessage());
			}
		}while (option != 7);
			scanner.close();
	}
	private void showMenu() {
		System.out.println("=====================================");
		System.out.println("||  Menu - NFLE Manager OOP/A1     ||");
		System.out.println("||  Team: Khaled Chammout and god  ||");
		System.out.println("=====================================");
		System.out.println("1. Display Teams ");
		System.out.println("2. Display Players ");
		System.out.println("3. Display Scheduled Games ");
		System.out.println("4. Add Team ");
		System.out.println("5. Add Player to Team ");
		System.out.println("6. Find Team ");
		System.out.println("7. Exit ");
	}
	private int getUserOption() {
		try {
			int option = Integer.parseInt(scanner.nextLine().trim());
			return option;
		} catch (NumberFormatException e) {
			System.out.println("Error: Please enter a valid number.");
			return -1;
		}
	}
	 public void findTeam(Scanner scanner, TeamManager teamManager) throws NFLException {
	     System.out.print("Enter team name to search: ");
	     String teamName = scanner.nextLine();
	     Team team = teamManager.findTeam(teamName);
	     if (team != null) {
	         System.out.println("Team found: " + team);
	     } else {
	         throw new NFLException("Team not found.");
	     }
	 }
	private void createTeam(Scanner scanner, TeamManager teamManager) throws NFLException {
		System.out.println("Enter team name: ");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) throw new NFLException("Team name can't be empty ");
		
		System.out.println("Enter city: ");
		String city = scanner.nextLine().trim();
		if (city.isEmpty()) throw new NFLException("City can't be empty");
		
		System.out.println("Enter coach name: ");
		String coachName = scanner.nextLine().trim();	
		if (coachName.isEmpty()) throw new NFLException("Coach name can't be empty ");
		
		System.out.println("Enter coach years of experience: ");
		 int yearsOfExperience = getValidInteger();
		 Coach coach = new Coach(coachName, yearsOfExperience);
	     Team team = new Team(name, city, coach);
		 
	     teamManager.addTeam(team);
	        System.out.println("Team added successfully!");
	    }
	 public static void addPlayerToTeam(Scanner scanner, PlayerManager playerManager, TeamManager teamManager) throws NFLException {
	     System.out.print("Enter player ID: ");
	     String playerId = scanner.nextLine();
	     System.out.print("Enter player name: ");
	     String playerName = scanner.nextLine();
	     System.out.print("Enter player age: ");
	     int playerAge = Integer.parseInt(scanner.nextLine());
	     System.out.print("Enter team name: ");
	     String teamName = scanner.nextLine();
	     System.out.print("Enter player position: ");
	     String playerPosition = scanner.nextLine();
	     
	     playerManager.addPlayer(playerId, playerName, playerAge, teamName, playerPosition, teamManager);
	 }

	    private int getValidInteger() throws NFLException {
	        try {
	            return Integer.parseInt(scanner.nextLine().trim());
	        } catch (NumberFormatException e) {
	            throw new NFLException("Invalid number format");
	        }
	    }
	private void exitProgram() {
		System.out.println("****************************************************");
		System.out.println("*   ...Exiting the program by Khaled Chammout...   *");
		System.out.println("****************************************************");
		scanner.close();
		System.exit(0);
	}

}
