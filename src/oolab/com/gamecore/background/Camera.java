package oolab.com.gamecore.background;

import org.lwjgl.input.Keyboard;

import oolab.com.gamecore.GameCore;
import oolab.com.gamecore.GameObject;
import oolab.com.gamecore.Renderer2D;
import oolab.com.gamecore.event.KeyboardEventArg;
import oolab.com.gamecore.event.KeyboardEventNotifier;

public class Camera implements GameObject{

	private Renderer2D renderer;
	private int x, y;
	private final int Step = 10;
	@Override
	public void Init(GameCore core) {
		renderer = core.getRenderer();
		x = y = 0;
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_UP;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                	y += Step;
                	renderer.SetCamera(x, y);
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_DOWN;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                	y -= Step;
                	renderer.SetCamera(x, y);
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_LEFT;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                	x -= Step;
                	renderer.SetCamera(x, y);
                }
        });
        core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {
                @Override
                public int WhichKey() {
                    return Keyboard.KEY_RIGHT;
                }
                @Override
                public void Handle(KeyboardEventArg arg) {
                	x += Step;
                	renderer.SetCamera(x, y);
                }
        });
	}

	@Override
	public void Render(Renderer2D renderer) {
	}

	@Override
	public void TickHandler() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NextTickHandler() {
		// TODO Auto-generated method stub
		
	}
	
}
