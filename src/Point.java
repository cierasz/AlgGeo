public class Point {

    private double x;
    private double y;
    private double z;

    public Point (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point addPointToPoint (Point p1, Point p2) {
        return new Point(   p1.getX() + p2.getX(),
                            p1.getY() + p2.getY(),
                            p1.getZ() + p2.getZ());
    }

    public static Point subtractPointToPoint (Point p1, Point p2) {
        return new Point(   p1.getX() - p2.getX(),
                            p1.getY() - p2.getY(),
                            p1.getZ() - p2.getZ());
    }

    public static Point multiplicationPointByVar (Point p, double var) {
        return new Point (  p.getX() * var,
                            p.getY() * var,
                            p.getZ() * var);
    }

    void setX(double x) {
        this.x = x;
    }

    double getX() {
        return this.x;
    }

    void setY(double y) {
        this.y = y;
    }

    double getY() {
        return this.y;
    }

    void setZ(double z) {
        this.z = z;
    }

    double getZ() {
        return this.z;
    }

}
