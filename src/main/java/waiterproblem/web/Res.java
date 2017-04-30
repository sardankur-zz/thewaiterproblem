package waiterproblem.web;

import waiterproblem.Point;

import java.util.List;
import java.util.Map;

public class Res {
    private List<Integer> order;
    private Map<String, Float> scores;

    public List<Integer> getOrder() {
        return order;
    }

    public void setOrder(List<Integer> order) {
        this.order = order;
    }

    public Map<String, Float> getScores() {
        return scores;
    }

    public void setScores(Map<String, Float> scores) {
        this.scores = scores;
    }
}
