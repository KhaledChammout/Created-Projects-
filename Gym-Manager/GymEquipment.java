package sadi9Lab2;

abstract public class GymEquipment {
    protected String name;

    // Constructor
    public GymEquipment(String name) {
        this.name = name;
    }

	// Abstract method
    public abstract void useEquipment();

    // Concrete method
    public void displayInfo() {
        System.out.print("Equipment: " + name);
    }
}
