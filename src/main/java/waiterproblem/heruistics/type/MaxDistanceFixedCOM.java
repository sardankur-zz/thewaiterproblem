package waiterproblem.heruistics.type;

import waiterproblem.Point;
import waiterproblem.heruistics.Heuristic;

import java.util.List;

public class MaxDistanceFixedCOM extends Heuristic {

    private Point com;

    public MaxDistanceFixedCOM(Point point) {
        super();
        com = new Point(point.x, point.y);
    }

    public Point getNextPoint(List<Point> points) {
        float maxDist = 0;
        Point next = null;
        for(Point p : points) {
            float dist = Point.getDistance(com, p);
            if(dist > maxDist) {
                maxDist = dist;
                next = p;
            }
        }
        return next;
    }
}
