package com.invictus.blocksworld.algorithm;

public class AStarAnalysis {

    private int iterationsCount;
    private int numberOfMoves = -1; //Because we want to exclude the initial state step
    private int maxFrontierSize = Integer.MIN_VALUE;

    public int getIterationsCount() {
        return iterationsCount;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int getMaxFrontierSize() {
        return maxFrontierSize;
    }

    public void setIterationsCount(int iterationsCount) {
        this.iterationsCount = iterationsCount;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public void setMaxFrontierSize(int maxFrontierSize) {
        this.maxFrontierSize = maxFrontierSize;
    }

    @Override
    public String toString() {
        return "AStarAnalysis{" +
                "iterationsCount=" + iterationsCount +
                ", numberOfMoves=" + numberOfMoves +
                ", maxFrontierSize=" + maxFrontierSize +
                '}';
    }
}