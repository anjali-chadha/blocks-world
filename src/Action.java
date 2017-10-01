/**
 * Defines the action taken while playing the game.
 * With reference to the Blocksworld, only one action is allowed.
 * Moving block from one stack to another.
 * @author zeus
 *
 */
public class Action {

	int from;
	int to;
	Action(int from, int to) {
	    this.from = from;
	    this.to = to;
    }

	String printPath() {
	    if (from == to) {
	        return "Initial State";
        }
		return "Block moved from "+ (from+1) + " to " + (to+1);
	}
}
