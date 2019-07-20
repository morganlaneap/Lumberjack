package com.gageryanplugins.lumberjack;

import java.io.File;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static String type;
	public static String tools;
	public static Main instance;
	public Events ec;
	
	public Main() {
		instance = this;
		ec = new Events();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		createConfig();
		type = getConfig().getString("type");
		tools = getConfig().getString("tools");
		getServer().getPluginManager().registerEvents(new Events(), this);
		
	}
	
	@SuppressWarnings("deprecation")
	private Integer gb(World world, int x, int y, int z) {
		return Integer.valueOf(world.getBlockAt(x, y, z).getTypeId());
	}
	
	public void breakChain(World w, int x, int y, int z) {
	    String ty, t = getConfig().getString("type");
	    
	    if (t.equals("classic") || t.equals("classic-leaves") || t.equals("full") || t.equals("full-noleaves")) {
	      ty = t;
	    } else {
	      return;
	    } 
	    w.getBlockAt(x, y, z).breakNaturally();
	    if (gb(w, x, y + 1, z).intValue() == 17 || gb(w, x, y + 1, z).intValue() == 162) {
	      breakChain(w, x, y + 1, z);
	    }
	    if (ty.equals("classic-leaves") || ty.equals("full")) {
	      
	      if (gb(w, x, y + 1, z).intValue() == 18 || gb(w, x, y + 1, z).intValue() == 161) {
	        breakChain(w, x, y + 1, z);
	      }
	      if (gb(w, x, y - 1, z).intValue() == 18 || gb(w, x, y - 1, z).intValue() == 161) {
	        breakChain(w, x, y - 1, z);
	      }
	      if (gb(w, x + 1, y, z).intValue() == 18 || gb(w, x + 1, y, z).intValue() == 161) {
	        breakChain(w, x + 1, y, z);
	      }
	      if (gb(w, x - 1, y, z).intValue() == 18 || gb(w, x - 1, y, z).intValue() == 161) {
	        breakChain(w, x - 1, y, z);
	      }
	      if (gb(w, x, y, z + 1).intValue() == 18 || gb(w, x, y, z + 1).intValue() == 161) {
	        breakChain(w, x, y, z + 1);
	      }
	      if (gb(w, x, y, z - 1).intValue() == 18 || gb(w, x, y, z - 1).intValue() == 161) {
	        breakChain(w, x, y, z - 1);
	      }
	    } 
	    if (ty.equals("full") || ty.equals("full-noleaves")) {
	      
	      if (gb(w, x, y - 1, z).intValue() == 17 || gb(w, x, y - 1, z).intValue() == 162) {
	        breakChain(w, x, y - 1, z);
	      }
	      if (gb(w, x + 1, y, z).intValue() == 17 || gb(w, x + 1, y, z).intValue() == 162) {
	        breakChain(w, x + 1, y, z);
	      }
	      if (gb(w, x - 1, y, z).intValue() == 17 || gb(w, x - 1, y, z).intValue() == 162) {
	        breakChain(w, x - 1, y, z);
	      }
	      if (gb(w, x, y, z + 1).intValue() == 17 || gb(w, x, y, z + 1).intValue() == 162) {
	        breakChain(w, x, y, z + 1);
	      }
	      if (gb(w, x, y, z - 1).intValue() == 17 || gb(w, x, y, z - 1).intValue() == 162)
	        breakChain(w, x, y, z - 1); 
	    } 
	  }
	
	private void createConfig()
	  {
	    try
	    {
	      if (!getDataFolder().exists()) {
	        getDataFolder().mkdirs();
	      }
	      File file = new File(getDataFolder(), "config.yml");
	      if (!file.exists())
	      {
	        getLogger().info("Config.yml not found, creating!");
	        saveDefaultConfig();
	      }
	      else
	      {
	        getLogger().info("Config.yml found, loading!");
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	 }

}
