package assignPack;
import java.io.Serializable;
import java.util.UUID;
public class Game implements Serializable {
	private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	
	private String team1;
	private String team2;
	private String date;
	private String location;
	private String score;
	
	public Game (String team1, String team2, String date, String location, String score) {
		this.team1 = team1;
		this.team2 = team2;
		this.date = date;
		this.location = location;
		this.score = score;
	}
	
	@Override 
	public String toString() {
		return String.format("%-10s %-10s %-10s %-15s %-10s", team1, team2, date, location, score);
		}
}
