import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Distances {

    private static double dot(Point p1, Point p2) {
        return ((p1.getX() * p2.getX()) +
                (p1.getY() * p2.getY()) +
                (p1.getZ() * p2.getZ())
        );
    }

    private static double norm(Point p) {
        return Math.sqrt(dot(p, p));
    }

    private static double d(Point p1, Point p2) {
        return norm(Point.subtractPointToPoint(p1,p2));
    }

    public static double distanceBetweenPointPoint(Point v1, Point v2) {
        return Math.sqrt(Math.pow(v2.getX() - v1.getX(), 2)
                + Math.pow(v2.getY() - v1.getY(), 2)
                + Math.pow(v2.getZ() - v1.getZ(), 2));
    }

    public static double distanceBetweenEdgeFace(Edge e, Face f1) {
        double p1 = distanceBetweenFacePoint(f1, e.v1);
        double p2 = distanceBetweenFacePoint(f1, e.v2);
        double e1 = distanceBetweenEdgeEdge(new Edge(f1.v1, f1.v2), e);
        double e2 = distanceBetweenEdgeEdge(new Edge(f1.v1, f1.v3), e);
        double e3 = distanceBetweenEdgeEdge(new Edge(f1.v2, f1.v3), e);

        List<Double> list = Arrays.asList(p1,p2,e1,e2,e3);

        return Collections.min(list);
    }

    public static double distanceBetweenSolidSolid(ArrayList<Face> s1, ArrayList<Face> s2) {
        double result = Double.MAX_VALUE;
        for (Face firstSolidFace : s1) {
            for (Face secondSolidFace : s2) {
                double distanceBetweenFaces = distanceBetweenFaceFace(firstSolidFace, secondSolidFace);
                if (result > distanceBetweenFaces) {
                    result = distanceBetweenFaces;
                }
            }
        }
        return result;
    }

    public static AABB getAABB(Solid solid) {
        double max = Double.MAX_VALUE;

        double minX = max;
        double minY = max;
        double minZ = max;
        double maxX = -max;
        double maxY = -max;
        double maxZ = -max;

        for (Face face : solid.solid) {
            for (Point point : Arrays.asList(face.v1, face.v2, face.v3)) {
                {
                    if (point.getX() > maxX) { maxX = point.getX(); }
                    if (point.getX() < minX) { minX = point.getX(); }
                    if (point.getY() > maxY) { maxY = point.getY(); }
                    if (point.getY() < minY) { minY = point.getY(); }
                    if (point.getZ() > maxZ) { maxZ = point.getZ(); }
                    if (point.getZ() < minZ) { minZ = point.getZ(); }
                }
            }
        }

        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static double distanceBetweenFaceFace(Face f1, Face f2) {
        double d1 = distanceBetweenPointPoint(f1.v1, f2.v1);
        double d2 = distanceBetweenPointPoint(f1.v1, f2.v2);
        double d3 = distanceBetweenPointPoint(f1.v1, f2.v3);

        double d4 = distanceBetweenPointPoint(f1.v2, f2.v1);
        double d5 = distanceBetweenPointPoint(f1.v2, f2.v2);
        double d6 = distanceBetweenPointPoint(f1.v2, f2.v3);

        double d7 = distanceBetweenPointPoint(f1.v3, f2.v1);
        double d8 = distanceBetweenPointPoint(f1.v3, f2.v2);
        double d9 = distanceBetweenPointPoint(f1.v3, f2.v3);

        double t11 = distanceBetweenFacePoint(f2, f1.v1);
        double t12 = distanceBetweenFacePoint(f2, f1.v2);
        double t13 = distanceBetweenFacePoint(f2, f1.v3);

        double t21 = distanceBetweenFacePoint(f1, f2.v1);
        double t22 = distanceBetweenFacePoint(f1, f2.v2);
        double t23 = distanceBetweenFacePoint(f1, f2.v3);

        Edge e11 = new Edge(f1.v1, f1.v2);
        Edge e12 = new Edge(f1.v1, f1.v3);
        Edge e13 = new Edge(f1.v2, f1.v3);

        Edge e21 = new Edge(f2.v1, f2.v2);
        Edge e22 = new Edge(f2.v1, f2.v3);
        Edge e23 = new Edge(f2.v3, f2.v3);

        double de11 = distanceBetweenEdgeEdge(e11,e21);
        double de12 = distanceBetweenEdgeEdge(e11,e22);
        double de13 = distanceBetweenEdgeEdge(e11,e23);

        double de21 = distanceBetweenEdgeEdge(e12,e21);
        double de22 = distanceBetweenEdgeEdge(e12,e22);
        double de23 = distanceBetweenEdgeEdge(e12,e23);

        double de31 = distanceBetweenEdgeEdge(e13,e21);
        double de32 = distanceBetweenEdgeEdge(e13,e22);
        double de33 = distanceBetweenEdgeEdge(e13,e23);

        List<Double> list = Arrays.asList(d1,d2,d3,d4,d5,d6,d7,d8,d9,t11,t12,t13,t21,t22,t23,de11,de12,de13,de21,de22,de23,de31,de32,de33);

        return Collections.min(list);

    }

    public static double distanceBetweenEdgePoint(Edge e, Point p) {

        Point v = Point.subtractPointToPoint(e.getV2(),e.getV1());
        Point w = Point.subtractPointToPoint(p,e.getV1());

        double c1 = dot(w,v);
        if (c1 <= 0)
            return d(p,e.getV1());
        double c2 = dot(v,v);
        if (c2 <= c1)
            return d(p,e.getV2());
        double b = c1 / c2;

        Point pointB = new Point(   (e.getV1().getX() + (b * v.getX())),
                (e.getV1().getY() + (b * v.getY())),
                (e.getV1().getZ() + (b * v.getZ())));

        return d(p, pointB);


    }

    public static double distanceBetweenEdgeEdge(Edge e1, Edge e2) {

        double SMALL_NUM = 0.00000001;

        Point u = Point.subtractPointToPoint(e1.getV2(), e1.getV1());
        Point v = Point.subtractPointToPoint(e2.getV2(), e2.getV1());
        Point w = Point.subtractPointToPoint(e1.getV1(), e2.getV1());

        double a = dot(u,u);
        double b = dot(u,v);
        double c = dot(v,v);
        double d = dot(u,w);
        double e = dot(v,w);
        double D = (a * c) - (b * b);
        double sc, sN, sD = D;
        double tc, tN, tD = D;

        if (D < SMALL_NUM) {
            sN = 0.0;
            sD = 1.0;
            tN = e;
            tD = c;
        } else {
            sN = (b*e - c*d);
            tN = (a*e - b*d);
            if (sN < 0.0) {
                sN = 0.0;
                tN = e;
                tD = c;
            }
            else if (sN > sD) {
                sN = sD;
                tN = e + b;
                tD = c;
            }
        }

        if (tN < 0.0) {
            tN = 0.0;
            if (-d < 0.0)
                sN = 0.0;
            else if (-d > a)
                sN = sD;
            else {
                sN = -d;
                sD = a;
            }
        } else if (tN > tD) {
            tN = tD;
            if ((0d + b) < 0.0)
                sN = 0;
            else if ((-d + b) > a)
                sN = sD;
            else {
                sN = (-d + b);
                sD = a;
            }
        }
        sc = (Math.abs(sN) < SMALL_NUM ? 0.0 : sN / sD);
        tc = (Math.abs(tN) < SMALL_NUM ? 0.0 : tN / tD);

        Point s1_sc = Point.multiplicationPointByVar(u,sc);
        Point s2_tc = Point.multiplicationPointByVar(v,tc);

        Point dP = Point.subtractPointToPoint(Point.addPointToPoint(w,s1_sc),s2_tc);
        return norm(dP);
    }

    public static double distanceBetweenFacePoint(Face f, Point p) {
        Point diff = Point.subtractPointToPoint(f.getV1(),p);
        Point edge0 = Point.subtractPointToPoint(f.getV2(),f.getV1());
        Point edge1 = Point.subtractPointToPoint(f.getV3(),f.getV1());

        double a = dot(edge0,edge0);
        double b = dot(edge0,edge1);
        double c = dot(edge1,edge1);
        double d = dot(edge0,diff);
        double e = dot(edge1,diff);
        double det = (a * c) - (b * b);
        double t0 = (b * e) - (c * d);
        double t1 = (b * d) - (a * e);

        if (t0 + t1 <= det)
        {
            if (t0 < 0.0)
            {
                if (t1 < 0.0)
                {
                    if (d < 0.0)
                    {
                        t1 = 0.0;
                        if (-d >= a)
                        {
                            t0 = 1.0;
                        }
                        else
                        {
                            t0 = -d / a;
                        }
                    }
                    else
                    {
                        t0 = 0.0;
                        if (e >= 0.0)
                        {
                            t1 = 0.0;
                        }
                        else if (-e >= c)
                        {
                            t1 = 1.0;
                        }
                        else
                        {
                            t1 = -e / c;
                        }
                    }
                }
                else
                {
                    t0 = 0.0;
                    if (e >= 0.0)
                    {
                        t1 = 0.0;
                    }
                    else if (-e >= c)
                    {
                        t1 = 1.0;
                    }
                    else
                    {
                        t1 = -e / c;
                    }
                }
            }
            else if (t1 < 0.0)
            {
                t1 = 0.0;
                if (d >= 0.0)
                {
                    t0 = 0.0;
                }
                else if (-d >= a)
                {
                    t0 = 1.0;
                }
                else
                {
                    t0 = -d / a;
                }
            }
            else
            {
                double invDet = 1.0 / det;
                t0 *= invDet;
                t1 *= invDet;
            }
        }
        else
        {
            double tmp0, tmp1, numer, denom;

            if (t0 < 0.0)
            {
                tmp0 = b + d;
                tmp1 = c + e;
                if (tmp1 > tmp0)
                {
                    numer = tmp1 - tmp0;
                    denom = a - ((double)2)*b + c;
                    if (numer >= denom)
                    {
                        t0 = 1.0;
                        t1 = 0.0;
                    }
                    else
                    {
                        t0 = numer / denom;
                        t1 = 1.0 - t0;
                    }
                }
                else
                {
                    t0 = 0.0;
                    if (tmp1 <= 0.0)
                    {
                        t1 = 1.0;
                    }
                    else if (e >= 0.0)
                    {
                        t1 = 0.0;
                    }
                    else
                    {
                        t1 = -e / c;
                    }
                }
            }
            else if (t1 < 0.0)
            {
                tmp0 = b + e;
                tmp1 = a + d;
                if (tmp1 > tmp0)
                {
                    numer = tmp1 - tmp0;
                    denom = a - ((double)2)*b + c;
                    if (numer >= denom)
                    {
                        t1 = 1.0;
                        t0 = 0.0;
                    }
                    else
                    {
                        t1 = numer / denom;
                        t0 = 1.0 - t1;
                    }
                }
                else
                {
                    t1 = 0.0;
                    if (tmp1 <= 0.0)
                    {
                        t0 = 1.0;
                    }
                    else if (d >= 0.0)
                    {
                        t0 = 0.0;
                    }
                    else
                    {
                        t0 = -d / a;
                    }
                }
            }
            else
            {
                numer = c + e - b - d;
                if (numer <= 0.0)
                {
                    t0 = 0.0;
                    t1 = 1.0;
                }
                else
                {
                    denom = a - ((double)2)*b + c;
                    if (numer >= denom)
                    {
                        t0 = 1.0;
                        t1 = 0.0;
                    }
                    else
                    {
                        t0 = numer / denom;
                        t1 = 1.0 - t0;
                    }
                }
            }
        }

        Point e0_t0 = Point.multiplicationPointByVar(edge0,t0);
        Point e1_t1 = Point.multiplicationPointByVar(edge1,t1);

        Point closestPoint = Point.addPointToPoint(f.getV1(),Point.addPointToPoint(e0_t0,e1_t1));
        diff = Point.subtractPointToPoint(p,closestPoint);
        return Math.sqrt(dot(diff,diff));


    }


}