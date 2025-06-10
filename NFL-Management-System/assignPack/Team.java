package assignPack;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Team implements Serializable{
	
	private String teamName;
	private String city;
	private Coach coach;
	private List<Player> players;
	
	public Team(String teamName, String city, Coach coach) {
		this.teamName = teamName;
		this.city = city;
		this.coach = coach;
		this.players = new ArrayList<>();
	}
	
	public String getTeamName() {
		return teamName;
	}
	 public String getCity() {
	     return city;
	 }

	 public Coach getCoach() {
	     return coach;
	 }

	 public List<Player> getPlayers() {
	     return players;
	 }

	public void addPlayer(Player player) throws NFLException {
		if(player == null) throw new NFLException("Invalid addition ");
		players.add(player);
	}
	
	@Override
	public String toString() {
		return String.format("%-10s %-15s %-30s %-5s", teamName, city, coach.toString(), players.size());
	}
	

}
