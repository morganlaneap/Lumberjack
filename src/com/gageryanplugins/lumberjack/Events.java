package com.gageryanplugins.lumberjack;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Events implements Listener {
	
	public Main plugin;
	
	public Events() {
		plugin = Main.getInstance();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLogBreak(BlockBreakEvent e) {
		int ih = e.getPlayer().getItemInHand().getTypeId();
		World tw = e.getPlayer().getWorld();
		int x = e.getBlock().getX();
		int y = e.getBlock().getY();
		int z = e.getBlock().getZ();
		plugin.reloadConfig();
		if (e.getBlock().getTypeId() == 17 || (e.getBlock().getTypeId() == 162 && this.plugin.getConfig().getIntegerList("tools").contains(Integer.valueOf(ih)))) {
			plugin.breakChain(tw, x, y, z);
		}
		
	}
}
