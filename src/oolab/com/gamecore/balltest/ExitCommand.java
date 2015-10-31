package oolab.com.gamecore.balltest;

import org.lwjgl.input.Keyboard;

import oolab.com.gamecore.GameCore;
import oolab.com.gamecore.GameObject;
import oolab.com.gamecore.Renderer2D;
import oolab.com.gamecore.event.KeyboardEventArg;
import oolab.com.gamecore.event.KeyboardEventNotifier;
import oolab.com.gamecore.event.WindowButtonEventNotifier;

public class ExitCommand implements GameObject{

	@Override
	public void Init(GameCore core) {
		/*ArrayList<Integer> keys = new ArrayList<Integer>(){{
			add(Keyboard.KEY_ESCAPE);
		}};*/
		core.getDispatcher().AddKeyboardEventNotifier(new KeyboardEventNotifier() {			
			@Override
			public int WhichKey() {
				return Keyboard.KEY_ESCAPE;
			}
			
			@Override
			public void Handle(KeyboardEventArg arg) {
				core.Exit();
			}
		});
		
		core.getDispatcher().AddWindowButtonEventNotifier(new WindowButtonEventNotifier() {
			@Override
			public void Handle() {
				core.Exit();
			}
		});
	}

	@Override
	public void Render(Renderer2D renderer) {
		assert false;
	}

	@Override
	public void TickHandler() {
		assert false;
	}

	@Override
	public void NextTickHandler() {
		assert false;
	}
}
