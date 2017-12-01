package com.invictus.blocksworld.heuristics;

import com.invictus.blocksworld.gameworld.Problem;
import com.invictus.blocksworld.gameworld.State;

public interface Heuristic
{
    int cost(State state, Problem problem);
}
