package waiterproblem;

import waiterproblem.heruistics.*;
import waiterproblem.heruistics.type.ClosestDistanceVariableCOM;
import waiterproblem.heruistics.type.MaxDistanceFixedCOM;
import waiterproblem.heruistics.type.MinDistanceFixedCOM;
import waiterproblem.heruistics.type.MinimizeCOMVariance;
import waiterproblem.score.*;
import waiterproblem.score.type.AreaConvexHullCOM;
import waiterproblem.score.type.DistFromCOM;
import waiterproblem.score.type.DistTravelledByCOM;
import waiterproblem.score.type.VarianceCOM;

import java.util.*;

public class Tests {

    List<String> heuristics = new ArrayList<>();
    List<String> scores = new ArrayList<>();
    HeuristicFactory heuristicFactory = new HeuristicFactory();
    ScoreFactory scoreFactory = new ScoreFactory();
    Algorithm algorithm = new Algorithm();

    public void setup() {
        heuristics.add(MinDistanceFixedCOM.class.getSimpleName());
        heuristics.add(ClosestDistanceVariableCOM.class.getSimpleName());
        heuristics.add(MaxDistanceFixedCOM.class.getSimpleName());
        heuristics.add(MinimizeCOMVariance.class.getSimpleName());

        scores.add(AreaConvexHullCOM.class.getSimpleName());
        scores.add(DistFromCOM.class.getSimpleName());
        scores.add(DistTravelledByCOM.class.getSimpleName());
        scores.add(VarianceCOM.class.getSimpleName());
    }

    public void test() {
        Map<String, Map<String, Float>> map = new HashMap<>();
        int T = 5000;
        int k = 20;

        for(int i = 0; i < T; ++i) {
            List<Point> points = Point.getRandomPoints(20);
            for (String heuristicName : heuristics) {
                Heuristic heuristic = heuristicFactory.getHeuristic(heuristicName, points);

                if(map.get(heuristicName) == null) {
                    map.put(heuristicName, new HashMap<>());
                }

                Map<String, Float> heuristicMap = map.get(heuristicName);
                for (String scorename : scores) {
                    Score score = scoreFactory.getScore(scorename, points);
                    Pair<List<Point>, Float> orderAndScore = algorithm.findOrderAndScore(points, k, heuristic, score);

                    if(heuristicMap.get(scorename) == null) {
                        heuristicMap.put(scorename, orderAndScore.getSecond());
                    } else {
                        heuristicMap.put(scorename, heuristicMap.get(scorename) + orderAndScore.getSecond());
                    }
                }
            }
        }

        for(String heuristicName : map.keySet()) {
            System.out.println(heuristicName);
            for(String scoreName : map.get(heuristicName).keySet()) {
                System.out.println(scoreName + " : " + (map.get(heuristicName).get(scoreName) / T));
            }
            System.out.println();
        }
    }

    public void test1() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(1,2));
        points.add(new Point(1.5f ,1.5f));
        points.add(new Point(2,1));
        points.add(new Point(2.5f,1.5f));
        points.add(new Point(2,2));

        Heuristic heuristic = new HeuristicFactory()
                .getHeuristic(ClosestDistanceVariableCOM.class.getSimpleName(), points);
        Score score = new ScoreFactory()
                .getScore(AreaConvexHullCOM.class.getSimpleName(), points);

        Algorithm algorithm = new Algorithm();
        Pair<List<Point>, Float> orderAndScore = algorithm.findOrderAndScore(points, 4, heuristic, score);
        System.out.println(orderAndScore.getSecond());
        for(Point point : orderAndScore.getFirst()) {
            System.out.println(point);
        }
    }

    public static void main(String[] args) {
        Tests tests = new Tests();
        tests.setup();
        tests.test();
    }
}
