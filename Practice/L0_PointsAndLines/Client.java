public class Client {
    public static void main(String[] args) {
        Point p = new Point(0, 0);

        System.out.println(p.distanceTo(new Point(0, 1)));  // should be 1.0
        System.out.println(p.distanceTo(new Point(1, 1)));  // should be sqrt(2) = 1.41...

        System.out.println(p.equals(new Point(0, 0)));  // should be true
        System.out.println(p.equals(new Point(0, 1)));  // should be false

        System.out.println(p);  // implicitly calls p.toString()
                                // should print (0, 0)


        // Write code to test your Line here
    }
}
