import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Error! Pass two arguments <blockCount> and <stackCount>");
            return;
        }
        int iterations = 1;
        List<AStarAnalysis> analysisList= new ArrayList<>(iterations);
        int blockCount = Integer.parseInt(args[0]);
        int stackCount = Integer.parseInt(args[1]) ;


        while(iterations > 0) {
            Problem problem = new Problem(blockCount, stackCount);
            AStarAnalysis analysis = AStarAlgorithm.runAStarAlgorithm(HeuristicFunctions::heuristicCost4, problem);
            analysisList.add(analysis);
            iterations --;
        }
        double mean_iterations_Count = analysisList.stream().mapToInt(x -> x.getIterationsCount()).average().getAsDouble();
        double mean_max_queue_size = analysisList.stream().mapToInt(x -> x.getMaxFrontierSize()).average().getAsDouble();
        double mean_path_length = analysisList.stream().mapToInt(x -> x.getNumberOfMoves()).average().getAsDouble();

        //System.out.println(mean_iterations_Count + " " + mean_max_queue_size + " " + mean_path_length);


        //AStarAlgorithm.runAStarAlgorithm(HeuristicFunctions::heuristicCost4, problem);
        //TODO: Write the test cases.
        //State goalState = problem.getGoalState();
        //problem.moveBlock(goalState, 0, 3);
        //problem.moveBlock(goalState, 0, 1);
        //problem.moveBlock(goalState, 0, 2);
        //State s = problem.initialState();
        //problem.successor(s);



        //TODO - Put in a test class for checking the State equals hash function.
            /*State s1 = problem.getGoalState();
            State s2 = problem.getGoalState();
            boolean equalityTest = s1.equals(s2);
            boolean equalityTest2 = s2.equals(s1);
            int hc1 = s1.hashCode();
            int hc2 = s2.hashCode();*/
            //1 |  A G C D B F I H E J Performs poorly
    }
}