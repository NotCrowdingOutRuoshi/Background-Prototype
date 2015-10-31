package oolab.com.gamecore.event;

public interface KeyboardEventNotifier{
	int WhichKey();
	void Handle(KeyboardEventArg arg);
}
