package assignPack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TeamManager {

	private List<Team> teams ;
	 public TeamManager() {
	     this.teams = new ArrayList<>();
	 }

	public void loadTeamsFromFile(String fileName) throws NFLException {
		//String fileName1 = "C:\\labFiles\\teams.csv";
		//team.loadTeamsFromFile(filename, player);
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if(parts.length == 4) {
					String teamName = parts[0].trim();
					String city = parts[1].trim();
					String coachName = parts[2].trim();
					int yearsExp = Integer.parseInt(parts[3].trim());
					Coach coach = new Coach(coachName, yearsExp);
					teams.add(new Team(teamName, city, coach));
				}
			}
		}catch(IOException e) {
			throw new NFLException("Invalid File ");
		}catch(NumberFormatException i) {
			throw new NFLException("Invalid file formating ");
		}
	}
	public void displayTeams() throws NFLException {
		if (teams.isEmpty() || teams == null) throw new NFLException("there are no teams to display ");
		System.out.println("-------------------------------------------------------------------");
	     System.out.printf("%-10s %-15s %-30s %-10s%n", "Team", "City", "Coach", "Players");
	     System.out.println("-------------------------------------------------------------------");
	     for (Team team : teams) {
	         System.out.println(team);
	     }
	}
	public void addTeam(Team team) {
	     teams.add(team);
	 }
	public Team findTeam(String teamName) throws NFLException {
		if (teamName == null ) throw new NFLException("Search term must not be empty ");
		for (Team team : teams) {
	         if (team.getTeamName().equalsIgnoreCase(teamName)) {
	             return team;
	         }
		}
		return null;
	}
}
