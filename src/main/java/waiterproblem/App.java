package waiterproblem;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import waiterproblem.heruistics.Heuristic;
import waiterproblem.heruistics.HeuristicFactory;
import waiterproblem.score.Score;
import waiterproblem.score.ScoreFactory;
import waiterproblem.score.type.AreaConvexHullCOM;
import waiterproblem.score.type.DistFromCOM;
import waiterproblem.score.type.DistTravelledByCOM;
import waiterproblem.score.type.VarianceCOM;
import waiterproblem.web.Req;
import waiterproblem.web.Res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;

public class App {

    List<String> scoreNames = new ArrayList<>();

    public App() {
        scoreNames.add(AreaConvexHullCOM.class.getSimpleName());
        scoreNames.add(DistFromCOM.class.getSimpleName());
        scoreNames.add(DistTravelledByCOM.class.getSimpleName());
        scoreNames.add(VarianceCOM.class.getSimpleName());
    }

    public Route apiCall = new Route() {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            Gson gson = new Gson();
            Req req = gson.fromJson(request.body(), Req.class);
            response.type("application/json");
            return gson.toJson(process(req));
        }
    };

    public void route() {
        port(8000);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        post("/api", apiCall);
    }

    public static void main(String[] args) {
        App app = new App();
        app.route();

    }

    public Res process(Req req) {
        Algorithm algorithm = new Algorithm();
        Heuristic heuristic = new HeuristicFactory().getHeuristic(req.getHeuristicName(), req.getPoints());
        ScoreFactory scoreFactory = new ScoreFactory();

        List<Score> scores = new ArrayList<>();
        for(String scoreName : scoreNames) {
            scores.add(scoreFactory.getScore(scoreName, req.getPoints()));
        }

        Pair<List<Point>, List<Float>> pair = algorithm.findOrderAndScores(req.getPoints(), req.getK(), heuristic, scores);

        Res res = new Res();
        res.setOrder(new ArrayList<>());
        for(int i = 0; i < req.getPoints().size(); ++i) {
            res.getOrder().add(i, pair.getFirst().indexOf(req.getPoints().get(i)));
        }

        res.setScores(new HashMap<>());
        for(int i = 0; i < scoreNames.size(); ++i) {
            res.getScores().put(scoreNames.get(i), pair.getSecond().get(i));
        }
        return res;
    }
}
