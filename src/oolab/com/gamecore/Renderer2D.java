package oolab.com.gamecore;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Renderer2D {
	
	ArrayList<GameObject> objs;
	DisplayMode mode;
	
	public Renderer2D() throws LWJGLException {
		objs = new ArrayList<GameObject>();
		switchMode();
		Display.create();
		glInit();
	}
	
	/**
	 * Called by GameCore to clean objects rendered in previous tick
	 */
	public void TickClear()
	{
		objs.clear();
	}
	
	/**
	 * GameObject can register itself to be rendered in this tick
	 * @param obj
	 */
	public void RequestRender(GameObject obj)
	{
		objs.add(obj);
	}
	
	/**
	 * Call by GameCore to render all underlying GameObject
	 */
	public void Render()
	{
		if(Display.isVisible())
		{
            glClear(GL_COLOR_BUFFER_BIT);
            for (GameObject obj : objs)
                    obj.Render(this);
            Display.update();
		}
	}
	
	public DisplayMode getDisplayMode()
	{
		return mode;
	}

	/**
	 * Retrieves a displaymode, if one such is available
	 *
	 * @param width
	 *            Required width
	 * @param height
	 *            Required height
	 * @param bpp
	 *            Minimum required bits per pixel
	 * @return
	 */
	private DisplayMode findDisplayMode(int width, int height, int bpp) throws LWJGLException {
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		for ( DisplayMode mode : modes ) {
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getBitsPerPixel() >= bpp && mode.getFrequency() <= 60 ) {
				return mode;
			}
		}
		return Display.getDesktopDisplayMode();
	}

	private void switchMode() throws LWJGLException {
		mode = findDisplayMode(800, 600, Display.getDisplayMode().getBitsPerPixel());
		Display.setDisplayModeAndFullscreen(mode);
	}

	/**
	 * Initializes OGL
	 */
	private void glInit() {
		// Enable texture
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
    	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		
		// Go into orthographic projection mode.
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, mode.getWidth(), 0, mode.getHeight());
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, mode.getWidth(), mode.getHeight());
		//set clear color to black
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//sync frame (only works on windows)
		Display.setVSyncEnabled(true);
	}
}
