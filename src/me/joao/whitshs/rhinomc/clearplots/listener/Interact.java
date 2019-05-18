package me.joao.whitshs.rhinomc.clearplots.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.intellectualcrafters.plot.api.PlotAPI;

import me.joao.whitshs.rhinomc.clearplots.Main;
import me.joao.whitshs.rhinomc.clearplots.object.ClearPlot;
import me.joao.whitshs.rhinomc.clearplots.utils.ItemHelper;

public class Interact implements Listener{
	
	PlotAPI plot = new PlotAPI();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		ItemStack item = e.getItem();

		if (item == null || item.getType() == Material.AIR) {
			return;
		}
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && item.isSimilar(ItemHelper.getItem())) {
			
			Location location = e.getClickedBlock().getLocation();
			if (plot.getPlot(location) == null || !plot.getPlot(location).hasOwner() || !plot.getPlot(location).isOwner(player.getUniqueId())) {
				player.sendMessage(Main.getInstance().getConfig().getString("messages.local").replace("&", "§"));
				return;
			}
			
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}else {
				player.getInventory().remove(item);
			}
			
			new ClearPlot(plot.getPlot(location), player);
			
		}
		
	}

}
