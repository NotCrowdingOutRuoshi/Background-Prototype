package oolab.com.gamecore.background;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

import oolab.com.gamecore.GameCore;
import oolab.com.gamecore.GameObject;
import oolab.com.gamecore.Renderer2D;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/***
 * consist of many tiles
 *
 */
public class Background implements GameObject {

	private class Tile
	{
		/***
		 * @param imgPath
		 * @param x render at x
		 * @param y render at y
		 */
		private int _renderX, _renderY, _tileW, _tileH;
		private Texture texture;
		public Tile(String imgPath, int x, int y) {
			_renderX = x; _renderY = y;
            try {
                    texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgPath));
            } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            _tileW = texture.getTextureWidth();
            _tileH = texture.getTextureHeight();
		}
		
		public Tile(Texture texture, int x, int y) {
			_renderX = x; _renderY = y;
			this.texture = texture;
            _tileW = texture.getTextureWidth();
            _tileH = texture.getTextureHeight();
		}

		public void Render()
		{
            glPushMatrix();
            glTranslatef(_renderX, _renderY, 0);

            texture.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 1);
                glVertex2i(0,      0);
                glTexCoord2f(0, 0);
                glVertex2i(0,      _tileH);
                glTexCoord2f(1, 0);
                glVertex2i(_tileW, _tileH);
                glTexCoord2f(1, 1);
                glVertex2i(_tileW, 0);
            }
            glEnd();
            
            glPopMatrix();
		}
	}
	//private Texture tileTexture;
	//private int tileWidth, tileHeight;
	private GameCore core;
	private Tile[][] tiles;

	public Background() {
		/*
		try {
			//tileTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Untitled.png"));
			tileTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/1_sss.png"));
			//tileTexture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/cool-background.jpg"));
			//tileTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cool-background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tileWidth = (int)(tileTexture.getImageWidth()/tileTexture.getWidth());
		tileHeight = (int)(tileTexture.getImageHeight()/tileTexture.getHeight());
		//tileWidth = tileTexture.getTextureWidth()
		//tileHeight = tileTexture.getTextureHeight()';
		System.out.println(Integer.toString(tileWidth));
		System.out.println(Integer.toString(tileHeight));
		System.out.println(Integer.toString(tileTexture.getTextureWidth()));
		System.out.println(Integer.toString(tileTexture.getTextureHeight()));
		System.out.println(Float.toString(tileTexture.getWidth()));
		System.out.println(Float.toString(tileTexture.getHeight()));
		*/
		// load 5 bg image
		Texture[] tileTextures = new Texture[5];

        try {
            for(int i = 1 ; i <= 5 ; i ++)
                tileTextures[i-1] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(String.format("res/%d_sss.png", i)));
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
		// new 50 x 20 Tile
        tiles = new Tile[20][50];
        for(int i = 0 ; i < 20 ; i ++)
        	for(int j = 0 ; j < 50 ; j ++)
        	{
        		tiles[i][j] = new Tile(tileTextures[(i+j)%5], j*128, i*128);
        	}
	}

	@Override
	public void Init(GameCore core) {
		this.core = core;
		core.getDispatcher().RegisterTickHandler(this);
	}

	@Override
	public void Render(Renderer2D renderer) {
        for(int i = 0 ; i < 20 ; i ++)
        	for(int j = 0 ; j < 50 ; j ++)
                tiles[i][j].Render();
		// render 3x3 tiles
		/*
        glPushMatrix();
        glTranslatef(100, 100, 0);

        tileTexture.bind();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 1*5);
            glVertex2i(0,         0);
            glTexCoord2f(0, 0);
            glVertex2i(0,         tileHeight*5);
            glTexCoord2f(1*5, 0);
            glVertex2i(tileWidth*5, tileHeight*5);
            glTexCoord2f(1*5, 1*5);
            glVertex2i(tileWidth*5, 0);
        }
        glEnd();
        
        glPopMatrix();
        */
        //glTranslatef(quadPosition.x, quadPosition.y, 0);
        //glRotatef(angle, 0.0f, 0.0f, 1.0f);
        //glColor3f(1.0f, 1.0f, 1.0f);

        /*
		for(int y = 0 ; y < 3 ; y ++)
            for(int x = 0 ; x < 3 ; x ++)
            {
            	
            }
        */
	}

	@Override
	public void TickHandler() {
		core.getRenderer().RequestRender(this);
	}

	@Override
	public void NextTickHandler() {
	}
}
