package lab05_302;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RentalSystem {

	private static final int MAX_VEHICLES = 20;
	private Vehicle[] inventory = new Vehicle[MAX_VEHICLES];
	private boolean[] rentedVehicles = new boolean[MAX_VEHICLES];
	private int vehicleCount = 0;
	private Scanner scanner = new Scanner(System.in);

	public void addVehicle(Vehicle vehicle) {
		if (vehicleCount < MAX_VEHICLES) {
			inventory[vehicleCount] = vehicle;
			rentedVehicles[vehicleCount] = false;
			vehicleCount++;
		}else {
			System.out.println("Inventory full!");
		}
	}

	public void displayInventory() { 
		if ( vehicleCount == 0) {
			System.out.println("Inventory is empty");
		}
		System.out.println("----- VEHICLE INVENTORY -----");
		for (int i = 0; i < vehicleCount; i++) {
/*
			 Ternary Operator (? and :):
				If rentedVehicles[i] is true, sets status = "[Rented]".
				If false, sets status = "[Available]"
*/
			String status = rentedVehicles[i] ? "[Rented]" : "[Available]";
/*
			 * (i + 1) used to display the vehicle number starting from 1 instead of 0
			 * inventory[i].getDetails calls the getter method to fetch info similarly to inventory[i].getDailyRate .
*/
			System.out.println((i + 1) + ". " + inventory[i].getDetails() + " - $"
					+ inventory[i].getDailyRate() + "/day " + status);
		}System.out.println("------------------------------");
	}

	public void rentedVehicle(int index) {
		if (index < 0 || index > vehicleCount) {
			System.out.println("Invalid selection ya 7mar ");
		}else if(!rentedVehicles[index - 1 ]){
			System.out.println("Vehicle is not rented ");
		}
		rentedVehicles[index - 1] = true;
		System.out.println(inventory[index - 1].rentVehicle());
		System.out.println("Vehicle #" + index + " has been rented successfully.");
	}

	public void returnVehicle(int index) {
		if (index < 1 || index > vehicleCount) {
			System.out.println("Invalid selection.");
			return;
		}
		if (!rentedVehicles[index - 1]) {
			System.out.println("Vehicle is not rented.");
			return;
		}
		rentedVehicles[index - 1] = false;
		System.out.println("Vehicle #" + index + " has been returned successfully.");
	}

	public void showMenu() {
		System.out.println("===== VEHICLE RENTAL SYSTEM ====");
		System.out.println("Option 1: View Available Vehicles ");
		System.out.println("Option 2: Rent a Vehicle");
		System.out.println("Option 3: Return a Vehicle");
		System.out.println("Option 4: Exit");
	}

	public void run() {
		addVehicle(new Car ("Nissan ", "Patrol ", 1989, 4, 400 ));
		addVehicle(new Car ("Cherokee ", "XJ ", 1989, 4, 250 ));
		addVehicle(new Motorcycle ("Honda ", "CBR500R ", 2022, 500, 35.99));
		addVehicle(new Motorcycle("Harley Davidson ", "Sportster ", 2023, 883, 55.99));

		try {
			while (true) {
			showMenu();
			System.out.print("Enter Your Choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				displayInventory();
				break;
			case 2:
				System.out.print("Enter the vehicle number to rent: ");
				int rentIndex = scanner.nextInt();
				rentedVehicle(rentIndex);
				break;
			case 3:
				System.out.print("Enter the vehicle number to return: ");
				int returnIndex = scanner.nextInt();
				returnVehicle(returnIndex);
				break;
			case 4:
				System.out.println("Thank you for using the Vehicle Rental System. Goodbye!");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
			}
		}catch (InputMismatchException e ) {
			System.out.println("invalid choice ya 7mar");

		}
	}
	public static void main(String[] args) {
		RentalSystem rentalSystem = new RentalSystem();
		rentalSystem.run();
	}
}




