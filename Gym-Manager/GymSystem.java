package sadi9Lab2;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class GymSystem {
    private static ArrayList<GymEquipment> equipmentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	try {
    	
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    displayAllEquipment();
                    break;
                case 3:
                    useAllEquipment();
                    break;
                case 4:
                    System.out.println("Thank you for using the Gym Equipment Management System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    	}
     catch (InputMismatchException e ) {
    	System.out.println("invalid choice");
    	scanner.nextLine();
    	}
    	
    }

    private static void displayMenu() {
        System.out.println("===== GYM EQUIPMENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add new equipment");
        System.out.println("2. Display all equipment");
        System.out.println("3. Use all equipment");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEquipment() {
    	
    	try {
    		System.out.println("What type of equipment would you like to add?");
        System.out.println("1. Treadmill");
        System.out.println("2. Dumbbell");
        System.out.print("Enter your choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter equipment name: ");
        String name = scanner.nextLine();

        if (typeChoice == 1) {
            System.out.print("Enter speed (km/h): ");
            double speed = scanner.nextDouble();
            equipmentList.add(new Treadmill(name, speed));
            System.out.println("Treadmill added successfully!");
        } else if (typeChoice == 2) {
            System.out.print("Enter weight (kg): ");
            double weight = scanner.nextDouble();
            equipmentList.add(new Dumbbell(name, weight));
            System.out.println("Dumbbell added successfully!");
        } else {
            System.out.println("Invalid choice.");
        }
    } catch (InputMismatchException e ) {
    	System.out.println("invalid choice");
    	scanner.nextLine();
    	
    }
    		
    		
    	}
        

    private static void displayAllEquipment() {
        System.out.println("===== EQUIPMENT LIST =====");
        for (GymEquipment equipment : equipmentList) {
            equipment.displayInfo();
        }
    }

    private static void useAllEquipment() {
        System.out.println("===== USING EQUIPMENT =====");
        for (GymEquipment equipment : equipmentList) {
            equipment.useEquipment();
        }
    }
}