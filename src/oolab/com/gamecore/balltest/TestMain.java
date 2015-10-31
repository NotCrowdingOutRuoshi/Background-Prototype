package oolab.com.gamecore.balltest;

import oolab.com.gamecore.GameCore;

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Change between fullscreen and windowed mode, by pressing F and W respectively");
		System.out.println("Move quad using arrowkeys, and change rotation using +/-");
		GameCore core = new GameCore();
		Ball ball = new Ball();
		ball.Init(core);
		ExitCommand exitControl = new ExitCommand();
		exitControl.Init(core);
		core.MainLoop();
	}
}
