package com.invictus.blocksworld.gameworld;

public class Block implements Comparable<Block>{

    private Character id;

    Block(char id) {
        this.id = new Character(id);
    }

    public char getId() {
        return id;
    }

    @Override
    public int compareTo(Block o) {
        return id.compareTo(o.id);
    }

	@Override
	public String toString() {
		return "{Block: " + id + "}";
	}


}