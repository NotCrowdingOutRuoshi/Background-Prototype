package oolab.com.gamecore;

import org.lwjgl.opengl.Display;

/*
 * Contains the main game loop logic.
 */
public class GameCore {
	
	Renderer2D renderer;
	
	public GameCore() {
		renderer = new Renderer2D();
	}
	/*
	 * Will block until exit game main body e.g. switch to login screen
	 */
	public void MainLoop () {
		while(true)
		{
			if (Display.isVisible()) {
				renderer.TickClear();
				DispatchEvent();
				// TODO: Invoke all GameTickNotifier
				
			}
			Display.update();
		}
	}
	
	/*
	 * Check any event and trigger event notifier for GameObjects change their Belief-Desire-Intention.  
	 */
	private void DispatchEvent()
	{
		// TODO: how to trigger all event in order
	}
	
	public void AddGameObject(GameObject obj)
	{
		obj.Init();
	}
	
	public Renderer2D getRenderer()
	{
		return renderer;
	}
}
