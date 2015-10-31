package oolab.com.gamecore;

/*
 * Contains the main game loop logic.
 */
public class GameCore {
	
	Renderer2D renderer;
	EventDispatcher dispatcher;
	boolean isExit;
	
	public GameCore() {
        isExit = false;
		try {
            renderer = new Renderer2D();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispatcher = new EventDispatcher();
	}

	/*
	 * Will block until exit game main body e.g. switch to login screen
	 */
	public void MainLoop () {
		while(!isExit)
		{
			renderer.TickClear();
			dispatcher.HandleEvents();
			dispatcher.HandleTickEvents();
			dispatcher.HandleTicks();
			renderer.Render();
		}
	}
	
	public Renderer2D getRenderer()
	{
		return renderer;
	}
	
	public EventDispatcher getDispatcher()
	{
		return dispatcher;
	}
	
	public void Exit()
	{
		isExit = true;
	}
	
}
