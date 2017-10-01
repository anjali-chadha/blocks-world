public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem(5, 3);
        Heuristic h = HeuristicFunctions::heuristicCost4;
        AStarAlgorithm.runAStarAlgorithm(h, problem);
        //AStarAlgorithm.runAStarAlgorithm(HeuristicFunctions::heuristicCost2, problem);
        //AStarAlgorithm.runAStarAlgorithm(HeuristicFunctions::heuristicCost4, problem);


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
    }
}