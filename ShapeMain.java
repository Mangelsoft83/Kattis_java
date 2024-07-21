import java.util.ArrayList;

public class ShapeMain {
    public static void main(String[] args) {


        Square square = new Square(5);
        Rect rect = new Rect(4,5);
        Circle circle = new Circle(4);

        ArrayList<Shape> list = new ArrayList<>();
        list.add(square);
        list.add(rect);
        list.add(circle);

        for (Shape item:list) {
            System.out.println(item.toString() + "  " + item.area() + " " + item.circumference());

        }
    }
}
