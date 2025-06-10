package sadi9Lab2;

public class Treadmill extends GymEquipment {
    private double speed;

    // Constructor
    public Treadmill(String name, double speed) {
        super(name);
        this.speed = speed;
    }

    // Implement abstract method
    @Override
    public void useEquipment() {
        System.out.println("Running on the treadmill at " + speed + " km/h.");
    }

    // Override displayInfo to include speed
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(", Speed: " + speed + " km/h");
        if (isFast()) {
            System.out.println("This treadmill is fast!");
        }
    }

    
    public boolean isFast() {
        return speed > 10;
    }
}
