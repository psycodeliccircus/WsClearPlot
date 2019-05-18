package me.joao.whitshs.rhinomc.clearplots.object;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.util.TaskManager;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;

import me.joao.whitshs.rhinomc.clearplots.Main;

public class ClearPlot {
	
	Plot plot;
	
	int maxZ, minZ;
	int maxX, minX;
	
	int x;
	int y;
	int z;
	
	int blocks;
	int task;
	
	World world;
	
	public ClearPlot(Plot plot, Player player) {
	
		this.plot = plot;

		world = Bukkit.getWorld(plot.getWorldName());

		Location bottom = plot.getBottomAbs();
		Location top = plot.getTopAbs();
		
		minX = bottom.getX();
		maxX = top.getX();
		
		minZ = bottom.getZ();
		maxZ = top.getZ();
		
		x = minX;
		z = minZ;
		
		y = top.getY();
		
		blocks = Main.getInstance().getConfig().getInt("configuration.base") * y;
		
		TaskManager.IMP.later(new Runnable() {
			
			public void run() {

				AsyncWorld w = AsyncWorld.wrap(world);
				
				for (int cx = 0; cx <= blocks; cx++) {
					
					w.getBlockAt(x, y, z).setType(Material.AIR);
					x++;
					if (x > maxX) {
						x = minX;
						z++;
						if (z > maxZ) {
							z = minZ;
							y--;
							if (y == 0) {
								if (player != null) {
									player.sendMessage(Main.getInstance().getConfig().getString("messages.cleared").replace("&", "§"));
									player.playSound(player.getLocation(), Sound.valueOf(Main.getInstance().getConfig().getString("sound")), 10, 1);
								}
								break;
							}
						}
					}
				}
				
				w.flush();
				
			}
		}, 1);
		
	}

}
