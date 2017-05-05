This codebase describes heuristics and scoring mechanism to evaluate the waiter problem.
It also has the an embedded webserver.

You will need to have maven and java to run this.
Install @ https://maven.apache.org/install.html

1. Go to the root directory which has the file pom.xml.
2. mvn clean install
3. Run the webserver App : 
    - mvn exec:java -Dexec.mainClass="waiterproblem.App" -Dexec.args="port"
4. Run the test :
    - mvn exec:java -Dexec.mainClass="waiterproblem.RunAllTests" 
-Dexec.args="number_of_iterations number_of_points number_of_points_to_add Heuristic Score"

example : 
Running app
mvn exec:java -Dexec.mainClass="waiterproblem.App" -Dexec.args="port"

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
