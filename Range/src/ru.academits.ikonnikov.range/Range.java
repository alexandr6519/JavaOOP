package ru.academits.ikonnikov.range;

public class Range {
    private double pointStart;
    private double pointEnd;
    private final double EPSILON = 1.0e-10;

    Range(double pointStart, double pointEnd) {
        if (pointStart - pointEnd > EPSILON) {
            System.out.println("Значение конечной точки не должно быть меньше значения начальной!!!");
        } else {
            this.pointStart = pointStart;
            this.pointEnd = pointEnd;
        }
    }

    double getPointStart() {
        return pointStart;
    }

    void setPointStart(double pointStart) {
        this.pointStart = pointStart;
    }

    double getPointEnd() {
        return pointEnd;
    }

    void setPointEnd(double pointEnd) {
        this.pointEnd = pointEnd;
    }

    double getLength() {
        if (this.pointEnd - this.pointStart < EPSILON) {
            return 0;
        }
        return this.pointEnd - this.pointStart;
    }

    boolean isInside(double pointChecked) {
        return pointChecked >= this.pointStart && pointChecked <= this.pointEnd;
    }

    boolean checkPointsRange() {
        if (pointStart - pointEnd > EPSILON) {
            System.out.println("Значение конечной точки не должно быть меньше значения начальной!!! Введите значения снова!");
            return true;
        }

        return false;
    }

    Range getIntersectionRanges(Range range) {
        if (Math.abs(this.pointStart - range.pointStart) < EPSILON && Math.abs(this.pointEnd - range.pointEnd) < EPSILON) {
            return range;
        }
        if ((Math.max(this.pointStart, range.pointStart) - Math.min(this.pointEnd, range.pointEnd) >= EPSILON)) {
            return null;
        }
        return new Range(Math.max(this.pointStart, range.pointStart), Math.min(this.pointEnd, range.pointEnd));
    }

    Range[] getUnionRanges(Range range) {
        if (Math.max(this.pointStart, range.pointStart) - Math.min(this.pointEnd, range.pointEnd) > EPSILON) {
            Range[] ranges = new Range[2];
            ranges[0] = this;
            ranges[1] = range;
            return ranges;
        } else {
            Range[] ranges = new Range[1];
            ranges[0] = new Range(Math.min(this.pointStart, range.pointStart), Math.max(this.pointEnd, range.pointEnd));
            return ranges;
        }
    }

    Range[] getDifferenceRanges(Range range) {
        if (Math.max(this.pointStart, range.pointStart) - Math.min(this.pointEnd, range.pointEnd) >= EPSILON) {
            Range ranges[] = new Range[1];
            ranges[0] = this;
            return ranges;
        }

        if ((range.pointStart - this.pointStart) > EPSILON) {
            if ((this.pointEnd - range.pointEnd) > EPSILON){
                Range ranges[] = new Range[2];
                ranges[0] = new Range(this.pointStart, range.pointStart);
                ranges[1] = new Range(range.pointEnd, this.pointEnd);
                return ranges;
            } else {
                Range ranges[] = new Range[1];
                ranges[0] = new Range(this.pointStart, range.pointStart);
                return ranges;
            }
        } else  if ((this.pointEnd - range.pointEnd) > EPSILON){
            Range ranges[] = new Range[1];
            ranges[0] = new Range(range.pointEnd, this.pointEnd);
            return ranges;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[%.2f ; %.2f]", this.pointStart, this.pointEnd);
    }
}





