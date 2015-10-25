package oolab.com.gamecore;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.List;

import oolab.com.gamecore.render.Quad;

public class Renderer2D {
	
	List<GameObject> objs;
	
	public Renderer2D() {
	}
	
	public void TickClear()
	{
		objs.clear();
	}
	
	public void RequestRender(GameObject obj)
	{
		objs.add(obj);
	}
	
	public void Render()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glPushMatrix();
		{
			for (GameObject obj : objs) {
				obj.Render();
			}
		}
		glEnd();
		glPopMatrix();
	}
}
