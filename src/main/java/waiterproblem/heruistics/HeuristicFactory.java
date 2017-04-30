package waiterproblem.heruistics;

import waiterproblem.Point;
import waiterproblem.heruistics.type.ClosestDistanceVariableCOM;
import waiterproblem.heruistics.type.MaxDistanceFixedCOM;
import waiterproblem.heruistics.type.MinDistanceFixedCOM;
import waiterproblem.heruistics.type.MinimizeCOMVariance;

import java.util.List;

public class HeuristicFactory {
    public Heuristic getHeuristic(String name, List<Point> points, Object ... args) {
        Point com = Point.getCOM(points);
        if(name.equals(MinDistanceFixedCOM.class.getSimpleName())) {
            return new MinDistanceFixedCOM(com);
        } else if(name.equals(ClosestDistanceVariableCOM.class.getSimpleName())) {
            return new ClosestDistanceVariableCOM(com);
        } else if(name.equals(MaxDistanceFixedCOM.class.getSimpleName())) {
            return new MaxDistanceFixedCOM(com);
        } else if(name.equals(MinimizeCOMVariance.class.getSimpleName())) {
            return new MinimizeCOMVariance(com);
        }
        return null;
    }
}
