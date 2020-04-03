package com.gageryanplugins.lumberjack;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

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

                if(getConfig().getInt("config-version") == 1) {

                    List<String> oldTools = getConfig().getStringList("tools");

                    for(int i = 0; i < oldTools.size(); i++) {
                        String tool = oldTools.get(i);

                        switch (tool) {
                            case "wooden":
                                oldTools.set(i, "WOODEN_AXE");
                                break;
                            case "stone":
                                oldTools.set(i, "STONE_AXE");
                                break;
                            case "iron":
                                oldTools.set(i, "IRON_AXE");
                                break;
                            case "golden":
                                oldTools.set(i, "GOLDEN_AXE");
                                break;
                            case "diamond":
                                oldTools.set(i, "DIAMOND_AXE");
                                break;
                        }
                    }

                    getConfig().set("config-version", 2);
                }

                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
