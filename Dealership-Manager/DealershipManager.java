package lab06p;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class DealershipManager {

	private ArrayList<Vehicle> vehicles = new ArrayList<>();
	
	public void addVehicle(Vehicle vehicle) throws VehicleException {
		if (vehicles == null) throw new VehicleException("Please add a vehicle");
		vehicles.add(vehicle);
		System.out.println("Vehicle was correctly added ");
	}
	public void loadVehiclesFromFile(String filename) throws VehicleException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))){
			String line;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",");
			if (parts.length == 4) {
				String make = parts[0].trim();
				String model = parts[1].trim();
				int year = Integer.parseInt(parts[2].trim());
				double price = Double.parseDouble(parts[3].trim());
				this.vehicles.add(new Vehicle(make, model, year, price));
			}
		}
	}catch (IOException e) {
		throw new VehicleException("Invalid File");
	}catch (NumberFormatException i) {
		throw new VehicleException("Invalid Formatting");
	}
}
	public void displayVehicles() throws VehicleException {
		
		System.out.println("🚗 All Vehicles 🚗");
		if (vehicles == null) throw new VehicleException("There is no vehicles to display ");

		Iterator<Vehicle> iterator = vehicles.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		//vehicles.forEach(vehicle ->System.out.println(vehicle));
		//for ( Vehicle vehicle : vehicles) {
		//System.out.println(vehicle);
		}
	
	public void findVehiclesByMake(String make) throws VehicleException {
		if (make.isEmpty()) throw new VehicleException("Search must not be empty ");
		boolean found = false;
		for (int i = 0; i < vehicles.size(); i++) {
			Vehicle vehicle = vehicles.get(i);
			if (vehicle.getMake().contains(make)) System.out.println(vehicle);
			found = true;
		}if (!found) System.out.println("there is no Vehicle ");
	}
	public void selectRandomVehicle() throws VehicleException {
		if (vehicles.isEmpty()) throw new VehicleException("Can't do that");
		Random random = new Random();
		Vehicle randomVehicle = vehicles.get(random.nextInt(vehicles.size()));
		System.out.println("🎲 Random Vehicle Selection 🎲");
		System.out.println("Selected Vehicle: " + randomVehicle);
	}
}

