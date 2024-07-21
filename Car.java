public class Car {
    private String licencePlate;
    private String color;
    private String brand;
    private String model;

    private double speed = 0;

    public void accelerate(double acc,double dt)
    {
        this.speed += acc * dt;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if (Math.abs(speed) < 300)
            this.speed = speed;
    }

    public Car(String licencePlate, String color, String brand, String model) {
        this.licencePlate = licencePlate;
        this.color = color;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
