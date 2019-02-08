package shapesPackage.classes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {

        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }
    
    @Override
    public double getHeight() {
        return sideLength;
    }
    
    @Override
    public double getArea() {     
        return sideLength * sideLength;
        
    }

    @Override
    public double getPerimeter() {       
        return  sideLength * 4;
        
    }

    @Override
    public String toString() {
        return String.format("class: Square; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {        
        return Double.hashCode(sideLength);
    }
    
    @Override
    public boolean equals(Object object) {
            if (object == this) {
                return true;
            }

            if (object.getClass() != this.getClass()) {
                return false;
            }

            Square square = (Square) object;

            return (this.sideLength == square.sideLength)
    }
}

