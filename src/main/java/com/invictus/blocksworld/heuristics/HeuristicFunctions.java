package com.invictus.blocksworld.heuristics;

import com.invictus.blocksworld.gameworld.Block;
import com.invictus.blocksworld.gameworld.BlockStack;
import com.invictus.blocksworld.gameworld.Problem;
import com.invictus.blocksworld.gameworld.State;

import java.util.*;

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
        List<Block> goalList = new ArrayList<>(problem.getGoalState().getStackList()
                .get(0).getStack());
        int n1 = goalList.size();
        int cost = n1;

        Deque<Block> stack = state.getStackList().get(0).getStack();
        List<Block> list = new ArrayList<>(stack);
        int n2 = list.size();

        for (int i = n1 - 1, j = n2 - 1; i >= 0 && j >= 0; i--, j--) {
            //Can use == for Blocks. Unique blocks created. Check BlockFactory
            if (goalList.get(i) == list.get(j)) {
                cost--;
            }
        }
        return cost;
    }

    /**
     * This heuristic adds 2 for every block that is not currently directly on
     * top of the block on which it has to be
     * in the goal state or if there is such a block somewhere below it (in the
     * same pile).
     */
    public static int heuristicCost3(State state, Problem problem) {
        int cost = 0;
        List<Block> goalList = new ArrayList<>(problem.getGoalState().getStackList()
                .get(0).getStack());
        int blockCount = problem.getBlockCount();
        List<BlockStack> stackList = state.getStackList();
        for (BlockStack stack : stackList) {
            List<Block> list = new ArrayList<>(stack.getStack());
            int n = list.size();

            for (int i = 0; i < n-1; i++) {
                int id1 = list.get(i).getId();
                int id2 = list.get(i+1).getId();
                if((id1-id2) != 1) {
                    cost +=2;
                }
            }
            if(list.size() != 0 && list.get(n-1).getId() != 'A') {
                cost +=2;
            }
        }
        return cost;
    }

    public static int heuristicCost4(State state, Problem problem) {
        List<Block> goalList = new ArrayList<>(problem.getGoalState().getStackList()
                .get(0).getStack());
        List<Block> correctBlocksStack1 = new ArrayList<>();
        int n1 = goalList.size();
        int misplacedBlocksCount = n1;

        Deque<Block> stack1 = state.getStackList().get(0).getStack();
        List<Block> list2 = new ArrayList<>(stack1);
        int n2 = list2.size();

        for(int i = n1-1, j = n2-1; i >= 0 && j >=0 ; i--, j--) {
            //Can use == for Blocks. Unique blocks created. Check BlockFactory
            if(goalList.get(i) == list2.get(j)) {
                correctBlocksStack1.add(goalList.get(i));
                misplacedBlocksCount--;
            }
        }
        int cost = 0;

        List<BlockStack> stackList = state.getStackList();
        for (BlockStack stack : stackList) {
            List<Block> list = new ArrayList<>(stack.getStack());
            if (!list.isEmpty()) {
                for (Block block : list) {
                    if (correctBlocksStack1.contains(block)) {
                        continue;
                    }
                    int movesOutOfCurrentStack = list.indexOf(block) + 1;
                    int correctBlockPosition = n1 - goalList.indexOf(block) - 1;
                    int moves = 0;
                    if (stackList.get(0) != null && stackList.get(0).getStack().size() > correctBlockPosition) {
                        moves = correctBlockPosition + misplacedBlocksCount;
                    }
                    cost += movesOutOfCurrentStack + moves;
                }
            }
        }
        cost = cost + cost;
        //Weighting strategy
        BlockStack stack0 = stackList.get(0);
        int n3 = stack0.getStack().size();
        if(n3 == 1) {
            for (Block block : stack0.getStack()) {
               // cost += block.getId() - 'A';
            }
        } else {
          /*f(problem.getStackCount() > 5) {
              //Giving more cost to a stack if the block at lower place is in correct position.
              List<Block> list0 = new ArrayList<>(stack0.getStack());
              if(n3 > 0 && list0.get(n3 - 1).getId() != 'A') {
                  cost += misplacedBlocksCount;
              }
          }*/

        }
        return cost;
    }
}
