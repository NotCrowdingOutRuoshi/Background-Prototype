package oolab.com.gamecore;

/*
 * Design in agent-orient, can register event notifier, can send render request to GameCore.
 */
public interface GameObject {
	/**
	 * Before add to GameEngin
	 */
	public void Init();
	
	public void Render();
}
