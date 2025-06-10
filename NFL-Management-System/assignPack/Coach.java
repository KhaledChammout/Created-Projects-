package assignPack;
import java.io.Serializable;
import java.util.UUID;
public class Coach implements Serializable{
	private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	
	private String name;
	private int yearsOfExperience;
	
	public String getName() {
		return name;
	}
	
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	
	public Coach(String name, int yearsOfExperience) {
		this.name = name;
		this.yearsOfExperience = yearsOfExperience; 
	}
	@Override
	public String toString() {
		 return String.format("%s (%d years)", name, yearsOfExperience); 
	}

}
