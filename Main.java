public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("AA-11","Red","Opel","Corsa");
        Car car2 = new Car("AA-12","Red","Opel","Corsa");

        System.out.println("car1 = " + car1);
        System.out.println("car2 = " + car2);
        double acc = 10;
        for (int i = 0; i < 10; i++) {
            car1.accelerate(10,1);
            System.out.println("car1.getSpeed() = " + car1.getSpeed());

            //accelerate(car2,1);
            //System.out.println("car2.getSpeed() = " + car2.getSpeed());
        }

    }


    public static void accelerate(Car car,double acelleration)
    {
        double actSpeed = car.getSpeed();;
        car.setSpeed(actSpeed + acelleration);
    }
}