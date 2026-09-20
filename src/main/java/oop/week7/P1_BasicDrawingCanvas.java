abstract class Shape {
    private final String shapeId;
    private static int counter = 0;

    protected Shape() {
        shapeId = "SH-" + (++counter);
    }

    public abstract double calculateArea();

    void scale(double factor) {
    }

    void scale(double xFactor, double yFactor) {
        scale(xFactor);
        scale(yFactor);
    }

    String getShapeId() {
        return shapeId;
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    void scale(double factor) {
        radius *= factor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    void scale(double factor) {
        side *= factor;
    }
}

public class P1_BasicDrawingCanvas {
    public static void main(String[] args) {
        CircleShape circle = new CircleShape(5);
        SquareShape square = new SquareShape(4);

        System.out.println(circle.getShapeId());
        Shape.printArea(circle);

        System.out.println(square.getShapeId());
        Shape.printArea(square);

        square.scale(2.0);
        Shape.printArea(square);

        circle.scale(2.0, 1.5);
        Shape.printArea(circle);
    }
}