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

public class RunAllTests {

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

    public void run(int T, int n, int k, List<String> heuristicNames, List<String> scoreNames) {
        Map<String, Map<String, Float>> map = new HashMap<>();
        for(int i = 0; i < T; ++i) {
            List<Point> points = Point.getRandomPoints(n);
            for (String heuristicName : heuristics) {
                if(heuristicNames.contains(heuristicName)) {
                    Heuristic heuristic = heuristicFactory.getHeuristic(heuristicName, points);

                    if (map.get(heuristicName) == null) {
                        map.put(heuristicName, new HashMap<>());
                    }

                    Map<String, Float> heuristicMap = map.get(heuristicName);
                    for (String scorename : scores) {
                        if(scoreNames.contains(scorename)) {
                            Score score = scoreFactory.getScore(scorename, points);
                            Pair<List<Point>, Float> orderAndScore = algorithm.findOrderAndScore(points, k, heuristic, score);

                            if (heuristicMap.get(scorename) == null) {
                                heuristicMap.put(scorename, orderAndScore.getSecond());
                            } else {
                                heuristicMap.put(scorename, heuristicMap.get(scorename) + orderAndScore.getSecond());
                            }
                        }
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

    public List<String> getHeuristics(String arg) {
        if(arg.equals("*")) {
            return heuristics;
        } else {
            return Arrays.asList(arg.split(","));
        }
    }

    public List<String> getScores(String arg) {
        if(arg.equals("*")) {
            return scores;
        } else {
            return Arrays.asList(arg.split(","));
        }
    }

    public static void main(String[] args) {
        RunAllTests tests = new RunAllTests();
        tests.setup();
        List<String> heuristicNames = tests.getHeuristics(args[3]);
        List<String> scoreNames = tests.getScores(args[4]);
        tests.run(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                Integer.parseInt(args[2]), heuristicNames, scoreNames);
    }
}
