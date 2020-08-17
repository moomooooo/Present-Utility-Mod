package me.present.modules;

import me.present.Present;
import me.present.events.Event;
import net.minecraft.client.Minecraft;

public class Module {
	
	public String name;
	public boolean toggled;
	private int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
	}
	
	public boolean isEnabled() {
		return toggled;
	}

	public int getKey() {
		return keyCode;
	}
	
	public void onEvent(Event e){
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled){
			onEnable();
		}else{
			onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public enum Category {
		COMBAT("Combat"),
		MOVEMENT("Movement"),
		RENDER("Render"),
		PLAYER("Player");
		
		public String name;
		public int moduleIndex;
		
		Category(String name) {
			this.name = name;
			
		}
		
public static int size(Category cat) {
			
			int i = 0;
			
			for(Module m : Present.modules) {
				if(m.category.equals(cat)) {
					i++;
				}
			}
			
			return i;
		}
		
		public static int placeInListCombat(Module m) {
			
			int i = 0;
			
			for(Module mod : Present.modules) {
				if(mod.category.equals(COMBAT) && !mod.equals(m)) {
					i++;
					continue;
				}
				
				if(mod.category.equals(COMBAT) && mod.equals(m)) {
					return i;
				}
			}
			
			return 0;
		}
		
		public static int placeInListMovement(Module m) {
			
			int i = 1;
			
			for(Module mod : Present.modules) {
				if(mod.category.equals(MOVEMENT) && !mod.equals(m)) {
					i++;
					continue;
				}
				
				if(mod.category.equals(MOVEMENT) && mod.equals(m)) {
					return i;
				}
			}
			
			return 0;
		}
		
		public static int placeInListRender(Module m) {
			
			int i = 1;
			
			for(Module mod : Present.modules) {
				if(mod.category.equals(RENDER) && !mod.equals(m)) {
					i++;
					continue;
				}
				
				if(mod.category.equals(RENDER) && mod.equals(m)) {
					return i;
				}
			}
			
			return 0;
		}

		public static int placeInListPlayer(Module m) {
		
		int i = 1;
		
		for(Module mod : Present.modules) {
			if(mod.category.equals(PLAYER) && !mod.equals(m)) {
				i++;
				continue;
			}
			
			if(mod.category.equals(PLAYER) && mod.equals(m)) {
				return i;
			}
		}
		
		return 0;
		}
	}
	
}
