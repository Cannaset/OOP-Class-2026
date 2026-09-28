public class BangunDatarDemo {
    public static void main(String[] args) {
        
        // 1. Create square using side constructor
        BangunDatar square = new BangunDatar(7.0);

        // 2. Create rectangle1 using default constructor, then setters
        BangunDatar rectangle1 = new BangunDatar();
        rectangle1.setLength(10.0);
        rectangle1.setWidth(5.0);

        // 3. Create rectangle2 using length & width constructor
        BangunDatar rectangle2 = new BangunDatar(12.0, 6.0);

        // Output must be descriptive
        System.out.println("--- Square ---");
        System.out.println("Area of square with side " + square.getSide() + " = " + square.calculateSquareArea());
        System.out.println("Perimeter of square with side " + square.getSide() + " = " + square.calculateSquarePerimeter());

        System.out.println("\n--- Rectangle 1 ---");
        System.out.println("Area of rectangle with length " + rectangle1.getLength() + " and width " + rectangle1.getWidth() + " = " + rectangle1.calculateRectangleArea());
        System.out.println("Perimeter of rectangle with length " + rectangle1.getLength() + " and width " + rectangle1.getWidth() + " = " + rectangle1.calculateRectanglePerimeter());

        System.out.println("\n--- Rectangle 2 ---");
        System.out.println("Area of rectangle with length " + rectangle2.getLength() + " and width " + rectangle2.getWidth() + " = " + rectangle2.calculateRectangleArea());
        System.out.println("Perimeter of rectangle with length " + rectangle2.getLength() + " and width " + rectangle2.getWidth() + " = " + rectangle2.calculateRectanglePerimeter());
    }
}