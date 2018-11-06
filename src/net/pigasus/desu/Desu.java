package net.pigasus.desu;

import org.bukkit.plugin.java.JavaPlugin;

public class Desu extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new ArrowDrops(this), this);
	}
	
	@Override
	public void onDisable() {
	
	}
	
}
