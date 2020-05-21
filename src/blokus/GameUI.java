package blokus;

import java.awt.Graphics;

public class GameUI extends GameObject{
	
	private boolean gameStarted = false;
	private boolean gameInProgress = false;
	
	public GameUI() {
		
	}
	

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(!gameStarted) {
			renderStartScreen(g);
		} else if(gameInProgress) {
			renderGameProgress(g);
		} else {
			renderGameEnded(g);
		}
	}
	
	private void renderStartScreen(Graphics g) {
		
	}
	
	private void renderGameProgress(Graphics g) {
		
	}
	
	private void renderGameEnded(Graphics g) {
		
	}

}
