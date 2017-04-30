package waiterproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x; this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj) == true) {
            return true;
        } else {
            Point p = (Point) obj;
            return (this.x == p.x && this.y == p.y);
        }
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f]", x, y);
    }

    public static List<Point> getRandomPoints(int n) {
        return getRandomPoints(n, 0, 100, 0, 100);
    }

    public static List<Point> getRandomPoints(int n, float x1, float x2, float y1, float y2) {
        List<Point> points = new ArrayList<Point>(n);
        Random random = new Random();
        for(int i = 0; i < n; ++i) {
            points.add(new Point((random.nextFloat() * (x2 - x1)) + x1,
                    (random.nextFloat() * (y2 - y1)) + y1));
        }
        return  points;
    }

    public static Point getCOM(List<Point> points) {
        if(points.size() == 0) return null;
        float xsum = 0, ysum = 0;
        for(Point p: points) {xsum += p.x; ysum += p.y; }
        return new Point(xsum/points.size(), ysum/points.size());
    }

    public static float getDistance(Point p1, Point p2) {
        return (float) Math.sqrt( Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
