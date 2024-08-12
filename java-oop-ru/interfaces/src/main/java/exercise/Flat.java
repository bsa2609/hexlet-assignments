package exercise;

// BEGIN
public class Flat implements Home{
    public double area;
    public double balconyArea;
    public int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public String toString() {
        StringBuilder buildDescription = new StringBuilder("Квартира площадью ");
        buildDescription.append(String.valueOf(getArea()));
        buildDescription.append(" метров на ");
        buildDescription.append(String.valueOf(floor));
        buildDescription.append(" этаже");

        return buildDescription.toString();
    }

    @Override
    public double getArea() {
        return area + balconyArea;
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
