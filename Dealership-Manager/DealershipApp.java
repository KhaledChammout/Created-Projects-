package lab06p;

import java.util.Scanner;

public class DealershipApp {
	private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DealershipManager manager = new DealershipManager();
        
       try {
        manager.loadVehiclesFromFile("C:\\labFiles\\Vehicles.txt");
        
        manager.displayVehicles();
       
        System.out.print("\nEnter car make to search: ");
        String make = scanner.nextLine();
        System.out.println("🔍 Vehicles from " + make + " 🔍");
        manager.findVehiclesByMake(make);
        
        System.out.println();
        
        
        manager.selectRandomVehicle();
    }catch (VehicleException e) {
    	System.err.println("Error: " + e.getMessage());
    }finally {
    	scanner.close();
    }
}
    }
