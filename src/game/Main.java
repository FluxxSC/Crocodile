package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	
	public static final String gameName = "Rogue Together";
	public static final int windowWidth = 800;
	public static final int windowHeight = 600;
	public static final int play = 0;

	public Main(String gameName) {
		super(gameName);
		this.addState(new Play());
		
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		//this.getState(play).init(gc, this);
		//this.enterState(play);
		
	}
	
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Main(gameName));
			appgc.setDisplayMode(windowWidth, windowHeight, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
