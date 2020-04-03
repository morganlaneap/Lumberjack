package com.gageryanplugins.lumberjack;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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


    public void breakChain(Block block) {
        String ty, t = getConfig().getString("type");

        //TODO: Improve config value check
        if (t.equals("classic") || t.equals("classic-leaves") || t.equals("full") || t.equals("full-noleaves")) {
            ty = t;
        } else {
            return;
        }

        // Break initial block
        block.breakNaturally();

        cut(block, BlockFace.UP);
        cut(block, BlockFace.DOWN);

        if (ty.equals("classic-leaves") || ty.equals("full")) {
            cut(block, BlockFace.UP);
            cut(block, BlockFace.DOWN);
            cut(block, block.getX() + 1, block.getY(), block.getZ());
            cut(block, block.getX() - 1, block.getY(), block.getZ());
            cut(block, block.getX(), block.getY(), block.getZ() - 1);
            cut(block, block.getX(), block.getY(), block.getZ() + 1);
        }

        if (ty.equals("full") || ty.equals("full-noleaves")) {
            Block block1 = block.getRelative(BlockFace.UP).getRelative(BlockFace.UP);
            Block block2 = block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);

            cut(block, block.getFace(block1));
            cut(block, block.getFace(block2));

            cut(block, block.getX() + 2, block.getY(), block.getZ());
            cut(block, block.getX() - 2, block.getY(), block.getZ());
            cut(block, block.getX(), block.getY(), block.getZ() - 2);
            cut(block, block.getX(), block.getY(), block.getZ() + 2);
        }
    }

    public void cut(Block block, BlockFace blockFace) {
        cut(block, blockFace.getModX(), blockFace.getModY(), blockFace.getModZ());
    }

    public void cut(Block block, int x, int y, int z) {
        block = block.getRelative(x, y, z);
        if (!areLeaves(block.getType())) return;
        if (!isLog(block.getType())) return;
        breakChain(block);
    }

    // Returns boolean if material is of type Log or not.
    public boolean isLog(Material material) {
        switch (material) {
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
                return true;
        }

        return false;
    }

    // Returns boolean if material is of type Leaves or not.
    public boolean areLeaves(Material material) {
        switch (material) {
            case ACACIA_LEAVES:
            case BIRCH_LEAVES:
            case DARK_OAK_LEAVES:
            case JUNGLE_LEAVES:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
                return true;
        }

        return false;
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
