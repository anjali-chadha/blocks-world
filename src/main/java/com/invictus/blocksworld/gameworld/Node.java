package com.invictus.blocksworld.gameworld;

/**
 * This bean class is similar to the data structure used in the book
 * @author zeus
 *
 */
public class Node implements Comparable<Node>{

	private State state;
	private	Node parent;
	private int costToReachNode; // g(n)
	private int costToReachGoal; //h(n)

	public Node(State state, Node parent) {
	    this.state = state;
	    this.parent = parent;
    }

	public Node(State state, Node parent, int costToReachNode) {
		this.state = state;
		this.parent = parent;
		this.costToReachNode = costToReachNode;
	}

	//TODO: Remove the unwanted setters and getters
	public State getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public int getCostToReachNode() {
		return costToReachNode;
	}

    public void setCostToReachGoal(int costToReachGoal) {
        this.costToReachGoal = costToReachGoal;
    }

    public int getTotalCost(){
	    return costToReachGoal + costToReachNode;
    }

    @Override
    public int compareTo(Node o) {
		int a = getTotalCost();
		int b = o.getTotalCost();
	    return (a > b) ? 1 : (a < b) ? -1 :0;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

       return state != null ? state.equals(node.state) : node.state == null;

    }

    @Override
    public int hashCode() {
        int result = 31;
        result = (state != null ? result + 17 * state.hashCode() : 0);
        return result;
    }
}
