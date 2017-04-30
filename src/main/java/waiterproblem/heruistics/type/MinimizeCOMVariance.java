package waiterproblem.heruistics.type;

import waiterproblem.Point;
import waiterproblem.heruistics.Heuristic;

import java.util.ArrayList;
import java.util.List;

public class MinimizeCOMVariance extends Heuristic {

    private Point com;
    private List<Point> processed;

    public MinimizeCOMVariance(Point point) {
        com = point;
        processed = new ArrayList<>();
    }

    @Override
    public Point getNextPoint(List<Point> points) {
        if(processed.size() == 0) {
            float dist = Float.MAX_VALUE;
            Point next = null;
            for(Point point : points) {
                float minDist = Point.getDistance(com, point);
                if(minDist < dist) {
                    dist = minDist;
                    next = point;
                }
            }
            processed.add(next);
            return next;
        } else {
            List<Point> tempList = new ArrayList<>(processed);
            float dist = Float.MAX_VALUE;
            Point next = null;
            for (Point point : points) {
                tempList.add(point);
                Point nextCOM = Point.getCOM(tempList);
                float minDist = Point.getDistance(com, nextCOM);
                if(minDist < dist) {
                    dist = minDist;
                    next = point;
                }
                tempList.remove(point);
            }
            return next;
        }
    }
}
