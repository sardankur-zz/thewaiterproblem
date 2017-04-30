package waiterproblem;

import waiterproblem.heruistics.Heuristic;
import waiterproblem.score.Score;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    public Pair<List<Point>, Float> findOrderAndScore(List<Point> points, int k, Heuristic heuristic, Score score) {
        List<Point> ordering = new ArrayList<Point>();
        List<Point> clonePoints = new ArrayList<Point>(points);

        for(int i = 0; i < k; ++i) {
            Point point = heuristic.getNextPoint(clonePoints);
            score.process(point);
            clonePoints.remove(point); ordering.add(point);
        }
        return new Pair<>(ordering, score.getScore());
    }

    public Pair<List<Point>, List<Float>> findOrderAndScores(List<Point> points, int k, Heuristic heuristic, List<Score> scores) {

        List<Point> ordering = new ArrayList<Point>();
        List<Point> clonePoints = new ArrayList<Point>(points);
        List<Float> scoreValues = new ArrayList<>();

        for(int i = 0; i < k; ++i) {
            Point point = heuristic.getNextPoint(clonePoints);
            for(int j = 0; j < scores.size(); ++j) {
                scores.get(j).process(point);
            }
            clonePoints.remove(point); ordering.add(point);
        }

        for(int j = 0; j < scores.size(); ++j) {
            scoreValues.add(j, scores.get(j).getScore());
        }

        return new Pair<>(ordering, scoreValues);
    }
}
