package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		Client c1 = new Client("localhost", 6789, this);
		Client c2 = new Client("localhost", 6789, this);
		Server s = new Server(6789, this);
		
		s.start();
		c1.start();
		c2.start();
		
		
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	public int getID() {
		return 0;
	}

}
