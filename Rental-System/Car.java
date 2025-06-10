package lab05_302;

public class Car implements Vehicle{
	String make;
	String model;
	int year;
	int seats;
	double dailyRate;
	
	public Car(String make, String model, int year, int seats, double dailyRate ) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.seats = seats;
		this.dailyRate = dailyRate;
	}

	@Override
	public String rentVehicle() {
		return  "Renting a " + make + model + "car. Daily rate: $" + dailyRate ;
	}

	@Override
	public String getDetails() {
		return  "Car: " + make + model + "Year: " + year + "," +  "Seats: " + seats ;
	}

	@Override
	public double getDailyRate() {
		return dailyRate;
	}

	public int getSeats() {
		return seats;
	}
}
