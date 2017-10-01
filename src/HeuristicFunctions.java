import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://github.com/AnujTewari/Blocks-World-Puzzle/tree/master/Blocks%20World/src
 */
public class HeuristicFunctions {

    /**
     * Heuristic h0 - Giving equal weightage to all the states
     * @param state
     * @param problem
     * @return
     */
    public static int heuristicCost0(State state, Problem problem) {
        return 3;
    }

    /**
     * Heurisitc counting the number of blocks that are out of place
     * @param state
     * @return
     */
    public static int heuristicCost1(State state, Problem problem) {
        int blockCount = problem.getBlockCount();
        int cost = blockCount;
        Deque<Block> stack = state.getStackList().get(0).getStack();
        List<Block> list = new ArrayList<>(stack);
        int len = list.size();
        for (int i = len - 1; i > 0; i--) {
            if(list.get(i).compareTo(list.get(i - 1 )) > 0) {
                break;
            }
            cost --;
        }
        return cost;
    }

    /**
     * If a block is on top of an element
     * @param state
     * @param problem
     * @return
     */
    public static int heuristicCost2(State state, Problem problem) {
        int stackCount = problem.getStackCount();
        int cost = 0;

        for(BlockStack stack2: state.getStackList().subList(1,stackCount)) {
            List<Block> list = new ArrayList<>(stack2.getStack());
            int n = list.size();
            for(int i = 1; i < n;i++) {
                if(list.get(i).compareTo(list.get(i-1)) < 0) {
                    cost = cost + 4;
                } else {
                    cost = cost + 1;
                }
            }
        }
        List<Block> list2 = new ArrayList<>(state.getStackList().get(0).getStack());
        int n = list2.size();
        for(int i = 1; i < n; i++) {
            if(list2.get(i).compareTo(list2.get(i - 1)) > 0) {
                cost = cost + 2;
            }
        }
        return cost;
    }

    public static int heuristicCost3(State state, Problem problem) {
        int cost = 0;
        int stackCount = problem.getStackCount();

        List<BlockStack> stackList = state.getStackList();
        for(BlockStack stack: stackList.subList(1, stackCount)) {
            List<Block> list = new ArrayList<>(stack.getStack());
            int len = list.size();
            cost = cost + len * (len+1)/2;
        }

        List<Block> stack1_list = new ArrayList<>(stackList.get(0).getStack());
        int n = stack1_list.size() - 1;
        while (n > 0) {
            if(stack1_list.get(n).compareTo(stack1_list.get(n - 1)) < 0) {
                continue;
            }
            cost = cost + n + 1;
            n--;
        }
        return cost + heuristicCost1(state, problem);
    }

    public static int heuristicCost4(State state, Problem problem) {
        int cost = 0;
        List<Block> goalList = new ArrayList<>(problem.getGoalState().getStackList().get(0).getStack());
        int heur = 0;
        List<Block> heurList = new ArrayList<>();
        int blocksOutOfPlace = goalList.size();
        List<Block> list1 = new ArrayList<>(state.getStackList().get(0).getStack());
        if (!list1.isEmpty()) {

            int n = list1.size() - 1;
            for (int i = n; i >=0; i--) {
                if (goalList.get(i).equals(list1.get(i))) {
                    heurList.add(goalList.get(i));
                    blocksOutOfPlace--;
                } else {
                    break;
                }
            }
        }

        List<BlockStack> stackList = state.getStackList();
        for (BlockStack stack : stackList) {
            List<Block> list = new ArrayList<>(stack.getStack());
            if (list.isEmpty()) {
                continue;
            }
            for (Block c : list) {
                if (heurList.contains(c)) {
                    continue;
                }
                int stepsToGetOutOfStack = list.indexOf(c);
                int positionInGoalNode =  list.size() - goalList.indexOf(c);
                int stepsToPutItInCorrectPosn = 0;
                if (stackList.get(0) != null && stackList.get(0).getStack().size() > positionInGoalNode) {
                    stepsToPutItInCorrectPosn = stackList.get(0).getStack().size() - positionInGoalNode + blocksOutOfPlace;
                }
                cost += stepsToGetOutOfStack + stepsToPutItInCorrectPosn;
            }
        }
        return cost;
    }
}
