package com.gageryanplugins.lumberjack;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Events implements Listener {
	
	public Main plugin;
	
	public Events() {
		plugin = Main.getInstance();
	}
	@EventHandler
	public void onLogBreak(BlockBreakEvent e) {
		String ih = e.getPlayer().getInventory().getItemInMainHand().getType().toString();
		World tw = e.getPlayer().getWorld();
		Player p = e.getPlayer();
		int x = e.getBlock().getX();
		int y = e.getBlock().getY();
		int z = e.getBlock().getZ();
		plugin.reloadConfig();
	    String oak = "OAK_LOG";
		String soak = "STRIPPED_OAK_LOG";
		String spruce = "SPRUCE_LOG";
		String sspruce = "STRIPPED_SPRUCE_LOG";
		String birch = "BIRCH_LOG";
		String sbirch = "STRIPPED_BIRCH_LOG";
		String jungle = "JUNGLE_LOG";
		String sjungle = "STRIPPED_JUNGLE_LOG";
		String acacia = "ACACIA_LOG";
		String sacacia = "STRIPPED_ACACIA_LOG";
		String darkoak = "DARK_OAK_LOG";
		String sdarkoak = "STRIPPED_DARK_OAK_LOG";
		String broke = e.getBlock().getBlockData().getMaterial().toString();
		if (p.hasPermission("lumberjack.use")) {
			List<String> tool = this.plugin.getConfig().getStringList("tools");
			if (broke == oak ||
			(broke == soak) ||
		    (broke == spruce) ||
		    (broke == sspruce) ||
		    (broke == birch) ||
		    (broke == sbirch) ||
		    (broke == jungle) ||
		    (broke == sjungle) ||
		    (broke == acacia) ||
		    (broke == sacacia) ||
		    (broke == darkoak) ||
		    (broke == sdarkoak)) {
				if (((tool.contains("wooden")) && ih == "WOODEN_AXE") || 
				   ((tool.contains("stone")) && ih == "STONE_AXE") || 
				   ((tool.contains("iron")) && ih == "IRON_AXE") || 
				   ((tool.contains("golden")) && ih == "GOLDEN_AXE") || 
				   ((tool.contains("diamond")) && ih == "DIAMOND_AXE")) {
					plugin.breakChain(tw, x, y, z);
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}
		
	}
}