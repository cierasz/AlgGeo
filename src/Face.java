public class Face extends Edge{

    Point v3;

    Face(Point v1, Point v2, Point v3) {
        super(v1, v2);
        this.v3 = v3;
    }

    void setV3(Point v3) {
        this.v3 = v3;
    }

    Point getV3() {
        return this.v3;
    }
}
