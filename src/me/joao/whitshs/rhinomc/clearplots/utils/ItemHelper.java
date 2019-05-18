package me.joao.whitshs.rhinomc.clearplots.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.joao.whitshs.rhinomc.clearplots.Main;

public class ItemHelper {
	
	private static ItemStack itemP;
	
	public static ItemStack getItem(int amount) {
		
		ItemStack item = itemP.clone();
		item.setAmount(amount);
		
		return item;
		
	}
	
	
	public static ItemStack getItem() {
		
		return itemP;
		
	}
	
	public static void load() {
		
		@SuppressWarnings("deprecation")
		ItemBuilder item = new ItemBuilder(Material.getMaterial(Main.getInstance().getConfig().getInt("item.id")), 1, (short)Main.getInstance().getConfig().getInt("item.data"));
		
		item.name(Main.getInstance().getConfig().getString("item.name"));
		item.listLore(Main.getInstance().getConfig().getStringList("item.lore"));
		
		itemP = item.build();
		
	}

}
