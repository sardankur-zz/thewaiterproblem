This codebase describes heuristics and scoring mechanism to evaluate the waiter problem.
It also has the an embedded webserver.

You will need to have maven and java to run this.
Install @ https://maven.apache.org/install.html

1. Go to the root directory which has the file pom.xml.
2. Run the following, It will clean and compile source files : mvn clean install
3. Run the webserver App : 
    - mvn exec:java -Dexec.mainClass="waiterproblem.App" -Dexec.args="port"
    - The app will be running at http://localhost:port/cs555.html
4. Run the test :
    - mvn exec:java -Dexec.mainClass="waiterproblem.RunAllTests" 
-Dexec.args="number_of_iterations number_of_points number_of_points_to_add Heuristic Score"
    - The first argument specifies number of iterations needs to be run. 
    - The second argument specifies number of random points to generate
    - The third argument specifies how many points to add
    - The fourth argument specifies the heuristic to apply. Use * for running all the heuristic or comma separated heuristic codes  strings given below
    - The fifth argument specifies the scoring metric to apply. Use * for running all the scores or comma separated score codes strings given below

example : 
Running app
mvn exec:java -Dexec.mainClass="waiterproblem.App" -Dexec.args="8080"

Running test
mvn exec:java -Dexec.mainClass="waiterproblem.RunAllTests" 
-Dexec.args="1000 100 10 MinDistanceFixedCOM,ClosestDistanceVariableCOM AreaConvexHullCOM,DistFromCOM,DistTravelledByCOM,VarianceCOM"

mvn exec:java -Dexec.mainClass="waiterproblem.RunAllTests" 
-Dexec.args="1000 100 10 * *"

The following heruistics are supported
- MinDistanceFixedCOM
- ClosestDistanceVariableCOM
- MaxDistanceFixedCOM
- MinimizeCOMVariance

The following scoring mechnaisms are supported
- AreaConvexHullCOM
- DistFromCOM
- DistTravelledByCOM
- VarianceCOM
