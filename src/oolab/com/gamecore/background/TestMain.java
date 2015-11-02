package oolab.com.gamecore.background;

import oolab.com.gamecore.GameCore;
import oolab.com.gamecore.balltest.Ball;

public class TestMain {

	public static void main(String[] args) {
		System.out.println("Change between fullscreen and windowed mode, by pressing F and W respectively");
		System.out.println("Move quad using arrowkeys, and change rotation using +/-");
		GameCore core = new GameCore();
		Background bg = new Background();
		bg.Init(core);
		ExitCommand exitControl = new ExitCommand();
		exitControl.Init(core);
		Camera cam = new Camera();
		cam.Init(core);
		core.MainLoop();
	}
}
