package me.joao.whitshs.rhinomc.clearplots;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.joao.whitshs.rhinomc.clearplots.cmd.GiveClearPlotsCommand;
import me.joao.whitshs.rhinomc.clearplots.listener.Interact;
import me.joao.whitshs.rhinomc.clearplots.utils.ItemHelper;

public class Main extends JavaPlugin{
	
	private static Main plugin;
	
	public void onEnable() {
		
		plugin = this;
		saveDefaultConfig();
		
		getCommand("giveclearplot").setExecutor(new GiveClearPlotsCommand());
		Bukkit.getPluginManager().registerEvents(new Interact(), this);
		
		ItemHelper.load();
		
	}
	
	public void onDisable() {

	}
	
	public static Main getInstance() {
		return plugin;
	}

}
