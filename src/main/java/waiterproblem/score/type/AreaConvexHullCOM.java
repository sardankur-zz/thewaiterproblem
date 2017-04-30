package waiterproblem.score.type;

import waiterproblem.Point;
import waiterproblem.algo.ConvexHullArea;
import waiterproblem.algo.QuickHull;
import waiterproblem.score.Score;

import java.util.ArrayList;
import java.util.List;

public class AreaConvexHullCOM extends Score {

    private float area;
    public List<Point> points = new ArrayList<>();
    public List<Point> comPoints = new ArrayList<>();

    QuickHull quickHull = new QuickHull();
    ConvexHullArea areaHull = new ConvexHullArea();

    public float getScore() {
        return area;
    }

    public void process(Point point) {
        points.add(point);
        Point comPoint = Point.getCOM(points);
        comPoints.add(comPoint);
        List<Point> hullPoints = quickHull.quickHull(comPoints);
        area = areaHull.area(hullPoints);
    }
}
