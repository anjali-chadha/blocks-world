import java.util.*;

/**
 * Trying to implement A* as mentioned in the book
 *
 * @author zeus
 *
 */
public class AStarAlgorithm {
    private static AStarAlgorithm aStar;
    private PriorityQueue<Node> frontier;
    private Set<State> exploredStateSet;
    private Map<State, Integer> stateToCostMap; //TODO: Is this data structure really necessary?
    private AStarAnalysis aStarAnalysis;
    private Heuristic heuristic;


    private AStarAlgorithm(Heuristic heuristic) {
        this.heuristic = heuristic;
        //TODO: Try to give some initial capacity to avoid array copy
        frontier = new PriorityQueue<>();
        exploredStateSet = new HashSet<>();
        stateToCostMap = new HashMap<>();
        aStarAnalysis = new AStarAnalysis();
    }

    public static void runAStarAlgorithm(Heuristic h, Problem p) {
        if (aStar == null) {
            aStar = new AStarAlgorithm(h);
        }
        Node endNode = aStar.aStarSearch(p);
        aStar.traceback(endNode);
        System.out.println(aStar.aStarAnalysis.toString());
    }

    //TODO: Improve the object creation of child node using Builder pattern
    private Node aStarSearch(Problem problem) {
        Node initialNode = new Node(problem.getInitialState(), null);
        frontier.add(initialNode);
        while (!frontier.isEmpty()) {
            if(frontier.size() > aStarAnalysis.maxFrontierSize) {
                aStarAnalysis.maxFrontierSize = frontier.size();
            }

            Node currentNode = frontier.poll();
            State currentState = currentNode.getState();
            aStarAnalysis.iterationsCount++;
            if (problem.isGoalState(currentState)) {
                return currentNode; //Goal Node
            }
            exploredStateSet.add(currentState);
            stateToCostMap.remove(currentState);
            List<State> childStateList = problem.successor(currentState);
            for (State childState : childStateList) {
                int h_n = heuristic.cost(childState, problem);
                Node childNode = new Node(childState, currentNode,
                        currentNode.getCostToReachNode() + 1);
                childNode.setCostToReachGoal(h_n);
                int totalCost = childNode.getTotalCost();

                //First check - whether child state was previously visited or not?
                if (!exploredStateSet.contains(childState) && !frontier.contains(childNode)) {
                    frontier.add(childNode);
                   // childState.printState();
                    stateToCostMap.put(childState, totalCost);
                } else if (frontier.contains(childNode) && stateToCostMap.get(childState) > totalCost) {
                    //Second check - whether any child state is already present in the frontier, if yes, update its cost
                    stateToCostMap.put(childState, totalCost);
                    frontier.remove(childNode);
                    frontier.add(childNode);
                }
            }
        }
        return null; //Solution not found
    }

    void traceback(Node endNode) {
        Deque<Action> moveList = new ArrayDeque<>();
        Node current = endNode;
        while(current != null) {
            moveList.push(current.getState().getAction());
            current = current.getParent();
        }

        int size = moveList.size();
        while(size > 0) {
            Action a = moveList.pop();
            System.out.println(a.printPath());
            size --;
            aStarAnalysis.numberOfMoves++;
        }
    }


    static class AStarAnalysis {

        private int iterationsCount;
        private int numberOfMoves = -1; //Because we want to exclude the initial state step
        private int maxFrontierSize = Integer.MIN_VALUE;

        @Override
        public String toString() {
            return "AStarAnalysis{" +
                    "iterationsCount=" + iterationsCount +
                    ", numberOfMoves=" + numberOfMoves +
                    ", maxFrontierSize=" + maxFrontierSize +
                    '}';
        }
    }


}
