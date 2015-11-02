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
	
	private ArrayList<GameObject> objs;
	private DisplayMode mode;
	private boolean _isRendering;
	
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
			_isRendering = true;
            glClear(GL_COLOR_BUFFER_BIT);
            for (GameObject obj : objs)
                    obj.Render(this);
            Display.update();
            _isRendering = false;
		}
	}
	
	public DisplayMode getDisplayMode()
	{
		return mode;
	}
	
	/***
	 * Do not call this while rendering or matrix order will crash
	 * @param x
	 * @param y
	 */
	public void SetCamera(int x, int y)
	{
		assert !_isRendering;
		// pop camera matrix
		glPopMatrix();
		// push new camera matrix
		glPushMatrix();
		glTranslatef(-x, -y, 0);
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
		Display.setDisplayMode(mode);
		//Display.setDisplayModeAndFullscreen(mode);
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
		// default view port
		glViewport(0, 0, mode.getWidth(), mode.getHeight());
		
		// push initial camera matrix
		glPushMatrix();
		glLoadIdentity();
		//glTranslatef(0, 0, 0);

		
		//set clear color to black
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//sync frame (only works on windows)
		Display.setVSyncEnabled(true);
	}
}
