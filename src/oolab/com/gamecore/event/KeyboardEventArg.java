package oolab.com.gamecore.event;

import java.util.List;

public class KeyboardEventArg implements GameEventArg{
	public List<Integer> downKeycodes;
	
	public KeyboardEventArg(List<Integer> downKeycodes) {
		// TODO Auto-generated constructor stub
		this.downKeycodes = downKeycodes;
	}
}
