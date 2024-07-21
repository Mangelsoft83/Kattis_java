public class Square implements Shape{
    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }

    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side*side;
    }

    @Override
    public double circumference() {
        return 4*side;
    }
}
