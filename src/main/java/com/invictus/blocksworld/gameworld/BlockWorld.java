import java.util.PriorityQueue;

/*
 * 1) Implement A* search
 * 2) Develop cost
 * 3) Frontier as PQ. Queue sorted on basis of f(n)
 * 4) State representation as list of lists, array
 * 5) Initial state - randomly assigning blocks to stacks
 * 6) Goal state - All blocks alphatically on Stack 1.
 * 7) Can use BFS fro small number of blocks
 * 8) Default cost = number of blocks out of place
 * 9) Keep track of number of iterations or goal tests 
 * and max size of the frontier, number of moves
 * 10) Node class - representing the states in Blockworld.
 * 11) successor() - generating legal moves from given state
 * 12) problem_generator - generates random initial 
 * states for testing. [Start from goal state and perform
 * sequence of random moves.]
 * 13) Method for gtracking visited states.
 * 14) Check when multiple paths lead to same state. 
 * Queue will expand if the former paths are placed into 
 * frontier.
 * 15) Data Structure for storing visited states, search efficient.
 * 16) implement traceback() method
 * 17) Setting upper limit on the number of iterations
 * 
 */
public class BlockWorld {
	
	private State initialState;
	private State goalState;

	private PriorityQueue frontier = new  PriorityQueue();

	private String traceback(Node node) {
		
		//TODO- To be implemented
		return null;
	}
	
	// Randomly generate an initial state
	private void problemGenerator(int stackCount, int blockCount) {
		
	}

	//Method for calculating h(n)
	private float heuristicFunction() {
		return 0;
	}
	
	//Method for calculating f(n)
	private float costFunction() {
		return 0;
	}
	
	//TODO - Make Node and State nested classes or eliminate one of them completely
	class Node {
		
	}
	
	class State {
		
	}

}
