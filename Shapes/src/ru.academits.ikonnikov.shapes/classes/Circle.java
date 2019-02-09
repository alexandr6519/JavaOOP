package ru.academits.ikonnikov.shapes.classes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }
    
    @Override
    public double getHeight() {
        return radius * 2;
    }
    
    @Override
    public double getArea() {
        try {
            return Math.PI * radius * radius;
        } catch (NullPointerException e) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }
    
    @Override
    public double getPerimeter() {
        try {
            return Math.PI * radius * 2;
        } catch (NullPointerException e) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("class: Circle; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        hash =  Double.hashCode(radius);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
            if (object == this) {
                System.out.println(" true (This is the same object!)");
                return true;
            }

            if (object.getClass() != this.getClass()) {
                System.out.println("Classes of objects are different!");
                return false;
            }
        
            Circle circle = (Circle) object;

            if (this.radius == circle.radius) {
                return true;
            } else {
                System.out.println("Parameters of objects are different!");
                return false;
            }
     }
}


