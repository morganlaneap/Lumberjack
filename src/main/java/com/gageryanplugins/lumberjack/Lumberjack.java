package com.gageryanplugins.lumberjack;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;

public class Lumberjack extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        String minVersion = "1.13.0";

        if(!isServerVersionHigherOrEqual(minVersion, this.getServer().getVersion())) {
            this.getLogger().log(Level.SEVERE, "Your server version is not supported! " +
                    "Plugin needs at least server version " + minVersion + " to work!");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        // Create config-file
        createConfig();

        // Register Listener
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }

    private void createConfig() {
        //TODO: Clean-up
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

                    this.getLogger().log(Level.INFO, "Changed configuration to version 2! Now supporting all materials as tools.");

                    getConfig().set("config-version", 2);
                }

                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isServerVersionHigherOrEqual(String minVersion, String serverVersion) {
        String[] minParts = minVersion.split(".");
        String[] serverParts = serverVersion.split(".");

        for(int i = 0; i < 2; i++) {
            if(!isHigherOrEqual(minParts[i], serverParts[i])) return false;
        }

        return true;
    }

    private boolean isHigherOrEqual(String min, String check) {
        if(Integer.valueOf(check) >= Integer.valueOf(min)) return true;
        return false;
    }
}
