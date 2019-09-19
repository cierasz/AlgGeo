import java.util.ArrayList;

public class Main {
    public static void main(String argv[]) {

        ArrayList<Face> solid_1 = new Solid().readSolidFromFile("C:\\Users\\Cierasze\\IdeaProjects\\AlgGeo\\Solid_1_Cords");
        ArrayList<Face> solid_2 = new Solid().readSolidFromFile("C:\\Users\\Cierasze\\IdeaProjects\\AlgGeo\\Solid_2_Cords");

        Point f1p1 = new Point(1,0,0);
        Point f1p2 = new Point(0,1,0);
        Point f1p3 = new Point(0,0,0);

        Point f2p1 = new Point(100,0,0.13);
        Point f2p2 = new Point(0,100,0.13);
        Point f2p3 = new Point(-100,-100,0.13);

        Edge e1 = new Edge(new Point(1,1,1), new Point (2,2,2));
        Edge e2 = new Edge(new Point(1,1,0), new Point (22,12,2));

        Face f1 = new Face(f1p1,f1p2,f1p3);
        Face f2 = new Face(f2p1,f2p2,f2p3);

        System.out.println(Distances.distanceBetweenFaceFace(f1, f2));
        System.out.println(Distances.distanceBetweenSolidSolid(solid_1,solid_2));
    }
}
