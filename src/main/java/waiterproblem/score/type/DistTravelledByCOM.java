package waiterproblem.score.type;

import waiterproblem.Point;
import waiterproblem.score.Score;

import java.util.ArrayList;
import java.util.List;

public class DistTravelledByCOM extends Score {

    List<Point> points = new ArrayList<Point>();
    Point com = null;
    float dist = 0;

    public float getScore() {
        return dist;
    }

    public void process(Point point) {
        points.add(new Point(point.x, point.y));
        if(com == null) {
            com = new Point(point.x, point.y);
        } else {
            Point newCOM = Point.getCOM(points);
            dist += Point.getDistance(com, newCOM);
            com = newCOM;
        }
    }
}
