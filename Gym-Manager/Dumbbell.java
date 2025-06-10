package sadi9Lab2;

public class Dumbbell extends GymEquipment {
    private double weight;

    // Constructor
    public Dumbbell(String name, double weight) {
        super(name);
        this.weight = weight;
    }

    // Implement abstract method
    @Override
    public void useEquipment() {
        System.out.println("Lifting a dumbbell weighing " + weight + " kg.");
    }

    // Override displayInfo to include weight
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(", Weight: " + weight + " kg");
        if (isHeavy()) {
            System.out.println("This dumbbell is heavy!");
        }
    }

   
    public boolean isHeavy() {
        return weight > 10;
    }
}
