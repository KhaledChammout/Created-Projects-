package lab05_302;

public class Motorcycle implements Vehicle{
	String make;
	String model;
	int year;
	int engineSize;
	double dailyRate;
	
	public Motorcycle(String make, String model, int year, int engineSize, double dailyRate ) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.engineSize = engineSize;
		this.dailyRate = dailyRate;
	}

	@Override
	public String rentVehicle() {
		return  "Renting a " + make + model + " motorcycle. Daily rate: $" + dailyRate;
	}

	@Override
	public String getDetails() {
		return "Motorcycle: " + make + model + "Year: " + year + "Engine: " + engineSize + "cc";
	}

	@Override
	public double getDailyRate() {
		return dailyRate;
	}
	 
	public int getEngineSize() {
		return engineSize;
	}
}

