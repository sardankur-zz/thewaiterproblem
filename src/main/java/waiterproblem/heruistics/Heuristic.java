package waiterproblem.heruistics;

import waiterproblem.Point;

import java.util.List;

public abstract  class Heuristic {
    public abstract Point getNextPoint(List<Point> points);
}
