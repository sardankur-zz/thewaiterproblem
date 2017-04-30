package waiterproblem.algo;

import waiterproblem.Point;

import java.util.List;

public class ConvexHullArea {

    public float area(List<Point> points) {
        float area = 0;
        if(points.size() > 2) {
            area = 0;
            int j = points.size() - 1;
            for (int i = 0; i < points.size(); i++)  {
                area = area +  (points.get(j).x + points.get(i).x)
                                    * ( points.get(j).y - points.get(i).y);
                j = i;
            }
            return Math.abs(area/2);
        }
        return area;
    }
}
