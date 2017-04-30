package waiterproblem.web;

import waiterproblem.Point;

import java.util.List;

public class Req {

    private String heuristicName;
    private List<Point> points;
    private int k;

    public String getHeuristicName() {
        return heuristicName;
    }

    public void setHeuristicName(String heuristicName) {
        this.heuristicName = heuristicName;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
