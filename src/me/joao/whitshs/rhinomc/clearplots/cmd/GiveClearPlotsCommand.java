package me.joao.whitshs.rhinomc.clearplots.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.joao.whitshs.rhinomc.clearplots.Main;
import me.joao.whitshs.rhinomc.clearplots.utils.ItemHelper;

public class GiveClearPlotsCommand implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!sender.hasPermission("clearplot.admin")) {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.permission").replace("&", "§"));
			return true;
		}
		
		if (args.length != 2) {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.sintaxe").replace("&", "§"));
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		
		if (target == null) {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.online").replace("&", "§"));
			return true;
		}
		
		try {
			Integer.parseInt(args[1]);
		} catch (Exception e) {
			sender.sendMessage(Main.getInstance().getConfig().getString("messages.sintaxe").replace("&", "§"));
			return true;
		}
		
		target.getInventory().addItem(ItemHelper.getItem(Integer.valueOf(args[1])));
		sender.sendMessage(Main.getInstance().getConfig().getString("messages.given").replace("&", "§"));
		
		return false;
	}

}
