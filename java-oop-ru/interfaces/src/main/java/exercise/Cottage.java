package exercise;

// BEGIN
public class Cottage implements Home{
    public double area;
    public int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public String toString() {
        StringBuilder buildDescription = new StringBuilder(String.valueOf(floorCount));
        buildDescription.append(" этажный коттедж площадью ");
        buildDescription.append(String.valueOf(getArea()));
        buildDescription.append(" метров");

        return buildDescription.toString();
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int CompareTo(Home another) {
        if (getArea() > another.getArea()) {
            return 1;
        } else if (getArea() < another.getArea()) {
            return -1;
        }

        return 0;
    }
}
// END
