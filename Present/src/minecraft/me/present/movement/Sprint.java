package me.present.movement;

import org.lwjgl.input.Keyboard;

import me.present.events.Event;
import me.present.listeners.EventUpdate;
import me.present.modules.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("Sprint", Keyboard.KEY_V, Category.MOVEMENT);
	}
	
	public void onEnable() {

		if(mc.player.movementInput.moveFoward > 0) {
			mc.player.setSprinting(true);
		
		}
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e){
		if(e instanceof EventUpdate){
			if(e.isPre()) {
				
			}
		}
	}

}
