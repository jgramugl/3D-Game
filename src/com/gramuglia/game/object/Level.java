package com.gramuglia.game.object;

import java.util.Vector;

public class Level {
	
	private Vector<Wall> walls;
	protected String texture;
	protected int size;
	protected String george = "res/Monkey128x128.png";
	
	public Level(String texture, int size) {
		this.texture = texture;
		this.size = size;
		
		walls = new Vector<Wall>();
	}
	
	public void addWall(Wall wall) {
		walls.add(wall);
	}
	
	public Wall getWall(int i) {
		return walls.get(i);
	}

	public int getNumberOfWalls() {
		return walls.size();
	}
}
