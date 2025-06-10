package assignPack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

	private List<Game> games ;
	public GameManager() {
	     this.games = new ArrayList<>();
	 }
	public void loadGamesFromFile(String fileName) throws NFLException {
		try(BufferedReader reader = new BufferedReader(new FileReader(new File( fileName)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if(parts.length == 5 ) {
					String team1 = parts[0].trim();
					String team2 = parts[1].trim();
					String date = parts[2].trim();
					String location = parts[3].trim();
					String score = parts[4].trim();
					
					Game game = new Game(team1, team2, date, location, score);
					games.add(game);
				}
			}
		}catch(IOException e) {
			throw new NFLException("Invalid file ");

		}catch(NumberFormatException i) {
			throw new NFLException("Invalid file formating ");
		}
	}
	public void displayGames() throws NFLException {
		if (games.isEmpty()) throw new NFLException("There is no games");
		int count = 0; // Counter to track printed lines
		for (Game game : games) {
		    System.out.println(game);
		    count++; // Increment after printing
		    if (count >= 10) {
		        break; // Stop after 10 lines
		    }
		}
	}
}
