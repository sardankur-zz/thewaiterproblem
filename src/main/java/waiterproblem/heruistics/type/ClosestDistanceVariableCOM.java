package waiterproblem.heruistics.type;

import waiterproblem.Point;
import waiterproblem.heruistics.Heuristic;

import java.util.List;

public class ClosestDistanceVariableCOM extends Heuristic {

    Point comStart;
    boolean start = true;

    public ClosestDistanceVariableCOM(Point point) {
        comStart = new Point(point.x, point.y);
    }

    public Point getNextPoint(List<Point> points) {
        Point com = null;
        if(start) {
            com = comStart;
            start = false;
        } else {
            com = Point.getCOM(points);
        }

        float dist = Float.MAX_VALUE;
        Point next = null;
        for(Point point : points) {
            float newDist = Point.getDistance(com, point);
            if(newDist < dist) {
                dist = newDist;
                next = point;
            }
        }
        return next;
    }
}
