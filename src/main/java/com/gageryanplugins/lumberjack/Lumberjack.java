package com.gageryanplugins.lumberjack;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Lumberjack extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //TODO: Add message if used on non supported version and disable plugin

        // Create config-file
        createConfig();

        // Register Listener
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }

    private void createConfig() {

        //TODO: Check config version and add converter for old configuration schema

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
