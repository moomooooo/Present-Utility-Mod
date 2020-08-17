package me.present.render;

import java.util.List;

import org.lwjgl.input.Keyboard;

import me.present.Present;
import me.present.events.Event;
import me.present.listeners.EventKey;
import me.present.listeners.EventRenderGUI;
import me.present.listeners.EventUpdate;
import me.present.modules.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module {
	
	public int currentTab;
	public boolean expanded;
	
	public TabGUI() {
		super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
		
			Gui.drawRect(2, 12, 53, 71, 0x80000000);
			Gui.drawRect(3, 14 + currentTab * 15 - 1, 52, 14 + currentTab * 15 + 11, 0x9993d3d3);
			
			int count = 0;
			for(Category c : Module.Category.values()) {
				fr.drawStringWithShadow(c.name, 5, 15 + count * 15, -1);
			
				count++;
				
			}
			
			if(expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Present.getModulesByCategory(category);
				
				if (modules.size() == 0)
					return;

				Gui.drawRect(54.5, 12, 130, 11 + modules.size() * 15, 0x80000000);
				Gui.drawRect(55.5, 14 + category.moduleIndex * 15 - 1, 129, 14 + category.moduleIndex * 15 + 11, 0x9993d3d3);
				
				count = 0;
				for(Module m : modules) {
					fr.drawStringWithShadow(m.name, 5 + 53, 15 + count * 15, -1);
				
					count++;
				}
				}
		}
		if(e instanceof EventKey) {
			int code = ((EventKey)e).code;

			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Present.getModulesByCategory(category);
			
			if(code == Keyboard.KEY_UP) {
				if(expanded) {
					if(category.moduleIndex <= 0) {
						category.moduleIndex = modules.size() - 1;
					}else
						category.moduleIndex--;
				}else {
					if(currentTab <= 0) {
						currentTab = Module.Category.values().length - 1;
					}else
						currentTab--;
				}
			}
			
			if(code == Keyboard.KEY_DOWN) {
				if (expanded) {
					if(category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
						}else
							category.moduleIndex++;
				}else {
					if(currentTab >= Module.Category.values().length - 1) {
						currentTab = 0;
						}else
							currentTab++;
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				if(expanded && modules.size() !=0) {
					Module module = modules.get(category.moduleIndex);
					if(!module.name.equals("TabGUI"))
						module.toggle();
				}else {
					expanded = true;
				}
			}
			
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
			}
		}
	}
}





