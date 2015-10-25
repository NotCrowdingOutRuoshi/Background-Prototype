package oolab.com.gamecore.render;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.opengl.Texture;

/**
 * 4 points in counter-clockwise order in 2D-plane form a Quad
 * 
 */
public class Quad {
	public int p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y;
	public Texture texture;
	public Quad(int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, Texture texture) {
		// TODO Auto-generated constructor stub
		this.p1x = p1x; this.p1y = p1y;
		this.p2x = p2x; this.p2y = p2y;
		this.p3x = p3x; this.p3y = p3y;
		this.p4x = p4x; this.p4y = p4y;
		this.texture = texture;
	}
	
	public void GlRender()
	{
		texture.bind();
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0,0);
			glVertex2i(-50, -50);
			glTexCoord2f(1,0);
			glVertex2i(50, -50);
			glTexCoord2f(1,1);
			glVertex2i(50, 50);
			glTexCoord2f(0,1);
			glVertex2i(-50, 50);
		}
		glEnd();
	}
}
