package waiterproblem.score.type;

import waiterproblem.Point;
import waiterproblem.score.Score;

public class DistFromCOM extends Score {

    Point com;
    float dist = 0;

    public DistFromCOM(Point point) {
        this.com = new Point(point.x, point.y);
    }

    public void process(Point point) {
        float d = Point.getDistance(point, com);
        if(d > dist) {
            dist = d;
        }
    }

    public float getScore() {
        return dist;
    }
}
