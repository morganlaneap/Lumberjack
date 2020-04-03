package com.gageryanplugins.lumberjack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

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

        // Check if player has permission
        if (!player.hasPermission("lumberjack.use")) return; //TODO: Add no permissions message

        // Check if tool is allowed
        if (!allowedTools.contains(player.getInventory().getItemInMainHand().getType())) return;

        // Check if Material is log
        if (!plugin.isLog(event.getBlock().getType())) return;

        Location location = event.getBlock().getLocation();
        plugin.breakChain(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}