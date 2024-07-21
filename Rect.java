public class Rect implements Shape{
    private int width;
    private int heigth;

    public Rect(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    @Override
    public double area() {
        return width*heigth;
    }

    @Override
    public String toString() {
        return "Rect{" +
                "width=" + width +
                ", heigth=" + heigth +
                '}';
    }

    @Override
    public double circumference() {
        return 2*width+2*heigth;
    }
}
