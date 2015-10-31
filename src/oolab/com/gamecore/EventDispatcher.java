package oolab.com.gamecore;

import java.util.HashSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import oolab.com.gamecore.event.KeyboardEventArg;
import oolab.com.gamecore.event.KeyboardEventNotifier;
import oolab.com.gamecore.event.WindowButtonEventNotifier;

public class EventDispatcher {
	private HashSet<GameObject> tickHandler;
	private HashSet<GameObject> nextTickHandler;
	private HashSet<KeyboardEventNotifier> keyboardNotifier;
	private HashSet<WindowButtonEventNotifier> windowButtonNotifier;
	
	public EventDispatcher() {
		tickHandler = new HashSet<GameObject>();
		nextTickHandler = new HashSet<GameObject>();
		keyboardNotifier = new HashSet<KeyboardEventNotifier>();
		windowButtonNotifier = new HashSet<WindowButtonEventNotifier>();
	}
	
	public void RegisterTickHandler(GameObject obj)
	{
		tickHandler.add(obj);
	}
	
	public void RegisterNextTickHandler(GameObject obj)
	{
		nextTickHandler.add(obj);
	}
	
	public void UnRegisterTickHandler(GameObject obj)
	{
		nextTickHandler.remove(obj);
	}
	
	public void UnRegisterNextTickHandler(GameObject obj)
	{
		nextTickHandler.remove(obj);
	}
	
	public KeyboardEventNotifier AddKeyboardEventNotifier(KeyboardEventNotifier notifier) {
		keyboardNotifier.add(notifier);
		return notifier;
	}
	
	public KeyboardEventNotifier RemoveKeyboardEventNotifier(KeyboardEventNotifier notifier) {
		keyboardNotifier.remove(notifier);
		return notifier;
	}

	public WindowButtonEventNotifier AddWindowButtonEventNotifier(WindowButtonEventNotifier notifier) {
		windowButtonNotifier.add(notifier);
		return notifier;
	}
	
	public WindowButtonEventNotifier RemoveWindowButtonEventNotifier(WindowButtonEventNotifier notifier) {
		windowButtonNotifier.remove(notifier);
		return notifier;
	}
	
	void HandleEvents() {
		// check keyboard event
		for(KeyboardEventNotifier notifier: keyboardNotifier)
			if(Keyboard.isKeyDown(notifier.WhichKey()))
				notifier.Handle(new KeyboardEventArg(null));
		// check window button
		if(Display.isCloseRequested())
		{
			for(WindowButtonEventNotifier notifier: windowButtonNotifier)
				notifier.Handle();
		}
		// TODO: mouse handler
		while(Mouse.next());
	}
	
	void TickClear()
	{
		nextTickHandler.clear();
	}
	
	void HandleTickEvents() {
		for(GameObject tick: nextTickHandler)
		{
			tick.NextTickHandler();
		}
	}
	
	void HandleTicks() {
		for(GameObject tick: tickHandler)
		{
			tick.TickHandler();
		}
	}
}
