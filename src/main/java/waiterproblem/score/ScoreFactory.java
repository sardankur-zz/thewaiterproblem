package waiterproblem.score;

import waiterproblem.Point;
import waiterproblem.score.type.AreaConvexHullCOM;
import waiterproblem.score.type.DistFromCOM;
import waiterproblem.score.type.DistTravelledByCOM;
import waiterproblem.score.type.VarianceCOM;

import java.util.List;

public class ScoreFactory {

    public Score getScore(String name, List<Point> points, Object ... args) {
        Point com = Point.getCOM(points);
        if(name.equals(DistFromCOM.class.getSimpleName())) {
            return new DistFromCOM(com);
        } else if(name.equals(DistTravelledByCOM.class.getSimpleName())) {
            return new DistTravelledByCOM();
        } else if(name.equals(AreaConvexHullCOM.class.getSimpleName())) {
            return new AreaConvexHullCOM();
        } else if(name.equals(VarianceCOM.class.getSimpleName())) {
            return new VarianceCOM(com);
        }

        return null;
    }
}
