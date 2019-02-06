package JavaOOP.shapes.classes;

import java.lang.NullPointerException;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {

        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        try {
            return sideLength * sideLength;
        } catch (NullPointerException e) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    public double getPerimeter() {
        try {
            return  sideLength * 4;
        } catch (NullPointerException e) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("class: Square; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        try {
            if (object == this) {
                System.out.println(" true (This is the same object!)");
                return true;
            }

            if (object.getClass() != this.getClass()) {
                System.out.println("Classes of objects are different!");
                return false;
            }

            Square square = (Square) object;

            if (this.sideLength == square.sideLength) {
                return true;
            } else {
                System.out.println("Parameters of objects are different!");
                return false;
            }

        } catch ( RuntimeException e ) {
            e.getMessage();
            return false;
        }
    }
}

