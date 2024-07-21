public class Circle implements Shape{
    int radius;


    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return (int) (radius * radius * Math.PI);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    @Override
    public double circumference() {
        return (int) (radius * 2 * Math.PI);
    }
}
