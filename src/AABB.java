public class AABB {

    private double min_x;
    private double min_y;
    private double min_z;
    private double max_x;
    private double max_y;
    private double max_z;

    AABB(double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
        this.min_x = min_x;
        this.min_y = min_y;
        this.min_z = min_z;
        this.max_x = max_x;
        this.max_y = max_y;
        this.max_z = max_z;
    }

    public double getMin_x() {
        return min_x;
    }

    public void setMin_x(double min_x) {
        this.min_x = min_x;
    }

    public double getMin_y() {
        return min_y;
    }

    public void setMin_y(double min_y) {
        this.min_y = min_y;
    }

    public double getMin_z() {
        return min_z;
    }

    public void setMin_z(double min_z) {
        this.min_z = min_z;
    }

    public double getMax_x() {
        return max_x;
    }

    public void setMax_x(double max_x) {
        this.max_x = max_x;
    }

    public double getMax_y() {
        return max_y;
    }

    public void setMax_y(double max_y) {
        this.max_y = max_y;
    }

    public double getMax_z() {
        return max_z;
    }

    public void setMax_z(double max_z) {
        this.max_z = max_z;
    }


}
