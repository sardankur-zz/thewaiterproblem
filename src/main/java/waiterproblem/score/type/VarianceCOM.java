package waiterproblem.score.type;

import waiterproblem.Point;
import waiterproblem.score.Score;

import java.util.ArrayList;
import java.util.List;

public class VarianceCOM extends Score {

    private float variance = 0;
    private Point com;
    private List<Point> processed;

    public VarianceCOM(Point point) {
        com = new Point(point.x, point.y);
        processed = new ArrayList<>();
    }

    @Override
    public float getScore() {
        return variance;
    }

    @Override
    public void process(Point point) {
        processed.add(point);
        variance += Math.pow(Point.getDistance(com, Point.getCOM(processed)), 2);
    }
}
