package oolab.com.gamecore;

/**
 * Belong to a specific GameObject,
 * containing list of event waiting to be triggered by GameEngine.
 * The internal events may be triggered by GameEngine in each tick of GameLoop.
 * And if multiple event was triggered in a single tick of GameLoop,
 * the callback will be called in the same order of its order in EventHandlerList.
 */
public class EventHandlerList {
	
}
