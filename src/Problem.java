import java.util.*;

public class Problem {
    private int blockCount;
    private int stackCount;
    private BlockFactory blockFactory;
    private State goalState;
    private State initialState;

    Problem(int blockCount, int stackCount) {
        this.blockCount = blockCount;
        this.stackCount = stackCount;
        blockFactory = new BlockFactory(blockCount);
        goalState = getGoalState();
    }

    /**
     * Creating an initial state.
     * Start from the goal state. Using random API, move blocks amongst stacks.
     */
    private State initialState() {
        Random r = new Random();
        int firstLoopCount = r.nextInt(blockCount) + 1;
        State state = createStateCopy(goalState);
        while(firstLoopCount > 0) {
            moveBlock(state, 0, r.nextInt(stackCount-1) + 1);
            firstLoopCount-- ;
        }

        int secondLoopCount = r.nextInt(stackCount * 50) + 100;
        while(secondLoopCount > 0) {
            int positionA = r.nextInt(stackCount);
            int positionB = r.nextInt(stackCount);
            moveBlock(state, positionA, positionB);
            secondLoopCount--;
        }
        state.setAction(new Action(0,0));//TODO
        System.out.print("Initial State: ");
        state.printState();
        return state;
    }

    /**
     * Original State:
     1 | B
     2 | C E
     3 | A D
     * @return
     */
    private State initialStateU() {
        List<BlockStack> stackList = new ArrayList<>();
        Deque<Block> stack1 = new ArrayDeque<>();
        Deque<Block> stack2 = new ArrayDeque<>();
        Deque<Block> stack3 = new ArrayDeque<>();
        Deque<Block> stack4 = new ArrayDeque<>();
        Deque<Block> stack5 = new ArrayDeque<>();
        stack1.push(blockFactory.getBlock('D'));
        stack2.push(blockFactory.getBlock('E'));
        stack2.push(blockFactory.getBlock('F'));
        stack2.push(blockFactory.getBlock('I'));
        stack2.push(blockFactory.getBlock('J'));
        stack3.push(blockFactory.getBlock('B'));
        stack3.push(blockFactory.getBlock('G'));
        stack4.push(blockFactory.getBlock('C'));
        stack4.push(blockFactory.getBlock('H'));
        stack5.push(blockFactory.getBlock('A'));
        stackList.add(new BlockStack(0,stack1));
        stackList.add(new BlockStack(1, stack2));
        stackList.add(new BlockStack(2, stack3));
        stackList.add(new BlockStack(3, stack4));
        stackList.add(new BlockStack(4, stack5));
        State state = new State(stackList);
        state.setAction(new Action(0,0));//TODO
        state.printState();
        return state;
    }

    /**
     * Goal state: All blocks are in alphabetical order on stack 1.
     * @return
     */
    public State getGoalState() {
        if(goalState == null) {
            List<Block> blockList = blockFactory.getBlockList();
            List<BlockStack> stackList = new ArrayList<>();
            int i = 0;
            //At 0 position null value.
            while (i < stackCount) {
                stackList.add(new BlockStack(i));
                i++;
            }
            Deque<Block> stack_0 = stackList.get(0).getStack();
            for (Block block : blockList) {
                stack_0.push(block);
            }
            return new State(stackList);
        }
        return goalState;
    }

    public State getInitialState() {
        if(initialState == null) {
            initialState = initialState();
        }
        return initialState;
    }

    //Do the hashcode check first, followed by equality
    //Time saving
    boolean isGoalState(State state) {
        return state.equals(goalState);
    }

    List<State> successor(State currentState) {
        List<State> childStateList = new ArrayList<>();
        for(int i = 0; i < stackCount; i++) {
            for(int j = 0; j < stackCount; j++) {
                if(i == j) continue;
                else {
                    State newState = createStateCopy(currentState);
                    boolean success = moveBlock(newState, i, j);
                    if(success) {
                        newState.setAction(new Action(i, j));
                        childStateList.add(newState);
                    }
                }
            }
        }
        return childStateList;
    }

    State createStateCopy(State state) {
        List<BlockStack> stackList = state.getStackList();
        List<BlockStack> newStackList = new ArrayList<>(stackCount);

        for(BlockStack stack: stackList) {
            BlockStack newStack = new BlockStack(stack.getId());
            newStack.getStack().addAll(stack.getStack());
            newStackList.add(newStack);
        }
        State newState = new State(newStackList);
        return newState;
    }

    boolean moveBlock(State s, int x, int y) {
        List<BlockStack> stackList = s.getStackList();
        BlockStack s1 = stackList.get(x);
        BlockStack s2 = stackList.get(y);
        if(s1 == null || s2 == null) {
            return false;
        }
        if(s1.getStack().isEmpty()) {
            return false;
        }
        Block b = s1.getStack().pop();
        s2.getStack().push(b);
        return true;
    }



    public int getBlockCount() {
        return blockCount;
    }

    public int getStackCount() {
        return stackCount;
    }

    public BlockFactory getBlockFactory() {
        return blockFactory;
    }
}