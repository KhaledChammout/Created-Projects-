package assignPack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

	private List<Player> players;
	public PlayerManager() {
		this.players = new ArrayList<>();
	}

	public void loadPlayersFromFile(String fileName, TeamManager teamManager) throws NFLException {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 5 ) {
					String playerID = parts[0].trim();
					String playerName = parts[1].trim();
					int playerAge = Integer.parseInt(parts[2].trim());
					String teamName = parts[3].trim();
					String position = parts[4].trim();

					Player player = new Player(playerID, playerName, playerAge, teamName, position );
					players.add(player);
					Team team = teamManager.findTeam(teamName);
	                if (team != null) {
	                    team.addPlayer(player);
	                }
				}
			}
		}catch(IOException e) {
			throw new NFLException("Invalid file ");

		}catch(NumberFormatException i) {
			throw new NFLException("Invalid file formating ");
		}
	}
	public void displayPlayers() throws NFLException {
		if (players.isEmpty()) throw new NFLException("there is no players to display ");
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-5s %-10s %-20s%n", "ID", "Player", "Age", "Team", "Position");
        System.out.println("-------------------------------------------------------------------");
        for (Player player : players) {
            System.out.println(player);
        }
	}
	public void addPlayer(String playerID , String name, int age, String teamName, String position, TeamManager teamManager) throws NFLException {
		if (playerID == null || playerID.trim().isEmpty()) throw new NFLException("Player id can't be empty ");
		if (name == null || name.trim().isEmpty()) throw new NFLException("Name can't be empty ");
		if (age <= 0 ) throw new NFLException("Age must be a positive number ");
		if (teamName == null || teamName.trim().isEmpty()) throw new NFLException("Team name can't be empty ");
		if (position == null || position.trim().isEmpty()) throw new NFLException("Position can't be empty ");

		Player player = new Player(playerID, name, age, teamName, position);
		players.add(player);
		Team team = teamManager.findTeam(player.getTeamName());
        if (team != null) {
            team.addPlayer(player);
	}
}
}

