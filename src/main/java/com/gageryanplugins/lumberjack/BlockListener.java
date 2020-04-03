package com.gageryanplugins.lumberjack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BlockListener implements Listener {

    private Lumberjack plugin;
    private List<Material> allowedTools;

    public BlockListener(Lumberjack plugin) {
        this.plugin = plugin;
        allowedTools = new ArrayList<>();

        // Load allowed tools from configuration file
		this.plugin.getConfig().getStringList("tools").forEach(tool -> {
			//TODO: Handle configuration error
			allowedTools.add(Material.valueOf(tool));
		});
	}

    @EventHandler
    public void onLogBreak(BlockBreakEvent event) {
    	Player player = event.getPlayer();

        if (!player.hasPermission("lumberjack.use")) return; //TODO: Add no permissions message
		if(!allowedTools.contains(player.getInventory().getItemInMainHand().getType())) return;

        switch (event.getBlock().getType()) {
            case OAK_LOG:
            case STRIPPED_OAK_LOG:
            case SPRUCE_LOG:
            case STRIPPED_SPRUCE_LOG:
            case BIRCH_LOG:
            case JUNGLE_LOG:
            case STRIPPED_JUNGLE_LOG:
            case ACACIA_LOG:
            case STRIPPED_ACACIA_LOG:
            case DARK_OAK_LOG:
            case STRIPPED_DARK_OAK_LOG:
				Location location = event.getBlock().getLocation();

            	plugin.breakChain(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
                break;
        }
    }
}