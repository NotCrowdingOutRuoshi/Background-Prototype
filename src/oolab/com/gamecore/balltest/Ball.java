package oolab.com.gamecore.balltest;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import static org.lwjgl.opengl.GL11.*;

import oolab.com.gamecore.GameCore;
import oolab.com.gamecore.GameObject;
import oolab.com.gamecore.Renderer2D;
import oolab.com.gamecore.event.KeyboardEventArg;
import oolab.com.gamecore.event.KeyboardEventNotifier;

public class Ball implements GameObject{

	private static final float MAX_SPEED = 20.0f;

	private Texture texture;

	private float angleRotation	= 1.0f;
	private Vector2f quadVelocity;
    private float angle;
    private Vector2f quadPosition;
    private int winWidth, winHeight;
    private GameCore core;

	@Override
	public void Init(GameCore core) {
		// init ball infor
		this.core = core;
        quadPosition = new Vector2f(100f, 100f);
        quadVelocity = new Vector2f(1.0f, 1.0f);
        angle = 0;
        winWidth = core.getRenderer().getDisplayMode().getWidth();
        winHeight = core.getRenderer().getDisplayMode().getHeight();
		
		// glInit load Texture
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/ball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_UP;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                    //quadVelocity.x += arrow_dirs[i].x;
                    quadVelocity.y += 0.1f;
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_DOWN;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                    //quadVelocity.x += arrow_dirs[i].x;
                    quadVelocity.y -= 0.1f;
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_LEFT;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                    quadVelocity.x -= 0.1f;
                    //quadVelocity.y += 0.1f;
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_RIGHT;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                    quadVelocity.x += 0.1f; 
                    //quadVelocity.y += 0.1f;
                }
        });
		
		core.getDispatcher().RegisterTickHandler(this);
	}

	@Override
	public void Render(Renderer2D renderer) {
        glPushMatrix();

        glTranslatef(quadPosition.x, quadPosition.y, 0);
        glRotatef(angle, 0.0f, 0.0f, 1.0f);
        glColor3f(1.0f, 1.0f, 1.0f);

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

        glPopMatrix();
	}

	@Override
	public void TickHandler() {
		/*
		if (quadVelocity.x < -MAX_SPEED) {
			quadVelocity.x = -MAX_SPEED;
		}
		if (quadVelocity.x > MAX_SPEED) {
			quadVelocity.x = MAX_SPEED;
		}
		if (quadVelocity.y < -MAX_SPEED) {
			quadVelocity.y = -MAX_SPEED;
		}
		if (quadVelocity.y > MAX_SPEED) {
			quadVelocity.y = MAX_SPEED;
		}
		angle += angleRotation;
		if (angle > 360.0f) {
			//angle = 0.0f;
		}
		quadPosition.x += quadVelocity.x;
		quadPosition.y += quadVelocity.y;
		//check colision with vertical border border
		if (quadPosition.x + 50 >= winWidth || quadPosition.x - 50 <= 0) {
			quadVelocity.x *= -1;
		}
		//check collision with horizontal border
		if (quadPosition.y + 50 >= winHeight || quadPosition.y - 50 <= 0) {
			quadVelocity.y *= -1;
		}

		angle += angleRotation;
		if (angle > 360.0f) {
			//angle = 0.0f;
		}
		quadPosition.x += quadVelocity.x;
		quadPosition.y += quadVelocity.y;
		//check colision with vertical border border
		if (quadPosition.x + 50 >= mode.getWidth() || quadPosition.x - 50 <= 0) {
			quadVelocity.x *= -1;
		}
		//check collision with horizontal border
		if (quadPosition.y + 50 >= mode.getHeight() || quadPosition.y - 50 <= 0) {
			quadVelocity.y *= -1;
		}
		*/
		// add to renderer
		core.getRenderer().RequestRender(this);
	}

	@Override
	public void NextTickHandler() {
		// will not be used
		assert false;
	}
}
