package com.gramuglia.game.object;

public class DemoLevel extends Level {
	
	public DemoLevel(String texture, int size) {
		super(texture, size);
		
		addWall(new Wall(-1, 1.5, 0, texture, size));
		addWall(new Wall(0, 1.5, 0, texture, size));
		
		addWall(new Wall(-1, 6.5, 0, texture, size));
		addWall(new Wall(0, 6.5, 0, texture, size)); 
		addWall(new Wall(1, 6.5, 0, texture, size));
		addWall(new Wall(2, 6.5, 0, texture, size));
		
		addWall(new Wall(-1.5, 1, 90, texture, size));
		addWall(new Wall(-1.5, 0, 90, texture, size));
		addWall(new Wall(-1.5, -1, 90, texture, size));
		
		addWall(new Wall(0.5, 2, 90, texture, size));
		addWall(new Wall(0.5, 3, 90, texture, size));
		
		addWall(new Wall(-1.5, 4, 90, texture, size));
		addWall(new Wall(-1.5, 5, 90, texture, size));
		addWall(new Wall(-1.5, 6, 90, texture, size));
		
		addWall(new Wall(-1, -1.5, 180, texture, size));
		addWall(new Wall(0, -1.5, 180, texture, size));
		addWall(new Wall(1, -1.5, 180, texture, size));
		addWall(new Wall(2, -1.5, 180, texture, size));
		
		addWall(new Wall(-1, 3.5, 180, texture, size));
		addWall(new Wall(0, 3.5, 180, texture, size));
		
		addWall(new Wall(2.5, -1, 270, texture, size));
		addWall(new Wall(2.5, 0, 270, texture, size));
		addWall(new Wall(2.5, 1, 270, texture, size));
		addWall(new Wall(2.5, 2, 270, texture, size));
		addWall(new Wall(2.5, 3, 270, texture, size));
		addWall(new Wall(2.5, 4, 270, texture, size));
		addWall(new Wall(2.5, 5, 270, texture, size));
		addWall(new Wall(2.5, 6, 270, texture, size));
		
		addWall(new Wall(2, 2, 0, george, size));
	}   
}
