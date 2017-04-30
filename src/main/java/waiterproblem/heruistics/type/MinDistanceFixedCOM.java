package waiterproblem.heruistics.type;

import waiterproblem.Point;
import waiterproblem.heruistics.Heuristic;

import java.util.List;

public class MinDistanceFixedCOM extends Heuristic {

    private Point com;

    public MinDistanceFixedCOM(Point point) {
        com = new Point(point.x, point.y);
    }

    public Point getNextPoint(List<Point> points) {
        float dist = Float.MAX_VALUE;
        Point next = null;
        for(Point point : points) {
            float minDist = Point.getDistance(com, point);
            if(minDist < dist) {
                dist = minDist;
                next = point;
            }
        }
        return next;
    }
}

