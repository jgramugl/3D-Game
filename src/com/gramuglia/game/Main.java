package com.gramuglia.game;

//
// Class to begin the game
//
public class Main {
	
	/**/
	/*
	public static void main(String[] args)
	
	NAME
		public static void main(String[] args) - Begin the game
	
	SYNOPSIS
		public static void main(String[] args)
	
	DESCRIPTION
		Create and begin the game.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public static void main(String[] args) {
		
		// Create a new game
		Game game = new Game();
		
		// Start the game
		game.start();
	}
}
