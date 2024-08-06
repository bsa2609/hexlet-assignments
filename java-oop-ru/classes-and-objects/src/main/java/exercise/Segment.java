package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int xBeginPoint;
        int yBeginPoint;

        if (beginPoint == null) {
            xBeginPoint = 0;
            yBeginPoint = 0;
        } else {
            xBeginPoint = beginPoint.getX();
            yBeginPoint = beginPoint.getY();
        }

        int xEndPoint;
        int yEndPoint;

        if (endPoint == null) {
            xEndPoint = 0;
            yEndPoint = 0;
        } else {
            xEndPoint = endPoint.getX();
            yEndPoint = endPoint.getY();
        }

        return new Point((xBeginPoint + xEndPoint) / 2, (yBeginPoint + yEndPoint) / 2);
    }
}
// END
