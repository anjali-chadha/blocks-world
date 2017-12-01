package com.invictus.blocksworld.algorithm;

import com.invictus.blocksworld.gameworld.Node;
import com.invictus.blocksworld.gameworld.Problem;
import com.invictus.blocksworld.gameworld.State;
import com.invictus.blocksworld.heuristics.Heuristic;

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

    public static AStarAnalysis runAStarAlgorithm(Heuristic h, Problem p) {
        if (aStar == null) {
            aStar = new AStarAlgorithm(h);
        }
        Node endNode = aStar.aStarSearch(p);
        aStar.traceback(endNode);
        System.out.println(aStar.aStarAnalysis.toString());
        AStarAnalysis starAnalysis = aStar.aStarAnalysis;
        aStar.cleanUp();
        return starAnalysis;
    }

    private void cleanUp() {
       aStar = null;
    }

    //TODO: Improve the object creation of child node using Builder pattern
    private Node aStarSearch(Problem problem) {
        Node initialNode = new Node(problem.getInitialState(), null);
        initialNode.setCostToReachGoal(heuristic.cost(initialNode.getState(), problem)); ;
        frontier.add(initialNode);
        while (!frontier.isEmpty()) {
            if(frontier.size() > aStarAnalysis.getMaxFrontierSize()) {
                aStarAnalysis.setMaxFrontierSize( frontier.size());
            }

            Node currentNode = frontier.poll();
            State currentState = currentNode.getState();
            if(aStarAnalysis.getIterationsCount() > 15000){
                return null;
            } else {
                //currentState.printState();
                aStarAnalysis.setIterationsCount(aStarAnalysis.getIterationsCount()+1);
                System.out.println("iter="+aStarAnalysis.getIterationsCount()+
                ", queue="+ aStarAnalysis.getMaxFrontierSize()+
                ", f=g+h="+ currentNode.getTotalCost()+
                ", depth="+ currentNode.getCostToReachNode());
            }

            if (problem.isGoalState(currentState)) {
                System.out.println("Success! total_goal_tests="+aStarAnalysis.getIterationsCount()+
                        ", max_queue_size="+ aStarAnalysis.getMaxFrontierSize()+
                        ", depth="+ currentNode.getCostToReachNode());
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
        Deque<State> moveList = new ArrayDeque<>();
        Node current = endNode;
        while(current != null) {
            moveList.push(current.getState());
            current = current.getParent();
        }

        System.out.println("Solution Path:");

        int size = moveList.size();
        while(size > 0) {
            moveList.pop().printState();
            size --;
            aStarAnalysis.setNumberOfMoves(aStarAnalysis.getNumberOfMoves()+1);
        }
    }
}
