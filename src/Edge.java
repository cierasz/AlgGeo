public class Edge {

    Point v1;
    Point v2;

    Edge(Point v1, Point v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    void setV1(Point v1) {
        this.v1 = v1;
    }

    Point getV1() {
        return this.v1;
    }

    void setV2(Point v2) {
        this.v2 = v2;
    }

    Point getV2() {
        return this.v2;
    }
}
