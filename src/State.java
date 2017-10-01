import java.util.List;

public class State {
	private final List<BlockStack> stackList;
	private int h_n; //Hueristic Function value

    /**
     * Represents the action that created this state.
     * This attribute doesn't contribute towards the equality or hashCode
     * calculation of the state.
     */
    private Action action;

	private volatile int hashCode; //Cached hashcode;

	State(List<BlockStack> o) {
		stackList = o;
	}

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public List<BlockStack> getStackList() {
		return stackList;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return stackList != null ? stackList.equals(state.stackList) : state.stackList == null;
    }

    @Override
    public String toString() {
        return "State{" +
                "stackList=" + stackList +
                '}';
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            if(stackList == null) return 0;
            result = 17;
            result = 31*result + stackList.hashCode();
            hashCode = result;
        }
	    return result;
    }

    void printState() {
        List<BlockStack> stacks = this.getStackList();
        System.out.println();
        for(BlockStack s: stacks) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(s.getId() + 1 + " || ");
            StringBuffer buffer2 = new StringBuffer();
            for(Block b: s.getStack()) {
                buffer2.insert( 0, " " + b.getId());
            }
            buffer.append(buffer2);
            System.out.println(buffer.toString());
        }
    }
}
