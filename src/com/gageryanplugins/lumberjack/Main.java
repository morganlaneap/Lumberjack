package com.gageryanplugins.lumberjack;

import java.io.File;

import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
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
	
	private String gb(World world, int x, int y, int z) {
		return String.valueOf(world.getBlockAt(x, y, z).getBlockData().getMaterial());
	}
	
	public void breakChain(World w, int x, int y, int z) {
	    String ty, t = getConfig().getString("type");
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
		
	    
	    if (t.equals("classic") || t.equals("classic-leaves") || t.equals("full") || t.equals("full-noleaves")) {
	      ty = t;
	    } else {
	      return;
	    } 
	    w.getBlockAt(x, y, z).breakNaturally();
	    if (gb(w, x, y + 1, z).toString() == oak ||
	    (gb(w, x, y + 1, z).toString() == soak) ||
	    (gb(w, x, y + 1, z).toString() == spruce) ||
	    (gb(w, x, y + 1, z).toString() == sspruce) ||
	    (gb(w, x, y + 1, z).toString() == birch) ||
	    (gb(w, x, y + 1, z).toString() == sbirch) ||
	    (gb(w, x, y + 1, z).toString() == jungle) ||
	    (gb(w, x, y + 1, z).toString() == sjungle) ||
	    (gb(w, x, y + 1, z).toString() == acacia) ||
	    (gb(w, x, y + 1, z).toString() == sacacia) ||
	    (gb(w, x, y + 1, z).toString() == darkoak) ||
	    (gb(w, x, y + 1, z).toString() == sdarkoak)) {
	      breakChain(w, x, y + 1, z);	      
	    }
	    if (ty.equals("classic-leaves") || ty.equals("full")) {
	    	String oleaves = "OAK_LEAVES";
		    String sleaves = "Material.SPRUCE_LEAVES";
		    String bleaves = "BIRCH_LEAVES";
		    String jleaves = "JUNGLE_LEAVES";
		    String aleaves = "ACACIA_LEAVES";
		    String doleaves = "DARK_OAK_LEAVES";
	      if (gb(w, x, y + 1, z).toString() == oleaves ||
	    	(gb(w, x, y + 1, z).toString() == sleaves) ||
	    	(gb(w, x, y + 1, z).toString() == bleaves) ||
	    	(gb(w, x, y + 1, z).toString() == jleaves) ||
	    	(gb(w, x, y + 1, z).toString() == aleaves) ||
	    	(gb(w, x, y + 1, z).toString() == doleaves)) {
	    	  breakChain(w, x, y + 1, z);
	      }
	      if (gb(w, x, y - 1, z).toString() == oleaves ||
	  	    (gb(w, x, y - 1, z).toString() == sleaves) ||
		   	(gb(w, x, y - 1, z).toString() == bleaves) ||
		   	(gb(w, x, y - 1, z).toString() == jleaves) ||
		   	(gb(w, x, y - 1, z).toString() == aleaves) ||
		   	(gb(w, x, y - 1, z).toString() == doleaves)) {
	    	  breakChain(w, x, y - 1, z);
	      }
	      if (gb(w, x + 1, y, z).toString() == oleaves ||
		  	 (gb(w, x + 1, y, z).toString() == sleaves) ||
		   	 (gb(w, x + 1, y, z).toString() == bleaves) ||
		  	 (gb(w, x + 1, y, z).toString() == jleaves) ||
			 (gb(w, x + 1, y, z).toString() == aleaves) ||
			 (gb(w, x + 1, y, z).toString() == doleaves)) {
	    	  breakChain(w, x + 1, y, z);
	      }
	      if (gb(w, x - 1, y, z).toString() == oleaves ||
	 		 (gb(w, x - 1, y, z).toString() == sleaves) ||
			 (gb(w, x - 1, y, z).toString() == bleaves) ||
			 (gb(w, x - 1, y, z).toString() == jleaves) ||
			 (gb(w, x - 1, y, z).toString() == aleaves) ||
			 (gb(w, x - 1, y, z).toString() == doleaves)) {
	    	  breakChain(w, x - 1, y, z);
	      }
	      if (gb(w, x - 1, y, z + 1).toString() == oleaves ||
	 	 	 (gb(w, x, y, z + 1).toString() == sleaves) ||
			 (gb(w, x, y, z + 1).toString() == bleaves) ||
			 (gb(w, x, y, z + 1).toString() == jleaves) ||
			 (gb(w, x, y, z + 1).toString() == aleaves) ||
			 (gb(w, x, y, z + 1).toString() == doleaves)) {
	    	  breakChain(w, x, y, z + 1);
	      }
	      if (gb(w, x - 1, y, z - 1).toString() == oleaves ||
	 	 	 (gb(w, x, y, z - 1).toString() == sleaves) ||
			 (gb(w, x, y, z - 1).toString() == bleaves) ||
			 (gb(w, x, y, z - 1).toString() == jleaves) ||
			 (gb(w, x, y, z - 1).toString() == aleaves) ||
			 (gb(w, x, y, z - 1).toString() == doleaves)) {
	    	  breakChain(w, x, y, z - 1);
	      }
	    } 
	    if (ty.equals("full") || ty.equals("full-noleaves")) {
	    	String oleaves = "Material.OAK_LEAVES";
		    String sleaves = "Material.SPRUCE_LEAVES";
		    String bleaves = "Material.BIRCH_LEAVES";
		    String jleaves = "Material.JUNGLE_LEAVES";
		    String aleaves = "Material.ACACIA_LEAVES";
		    String doleaves = "Material.DARK_OAK_LEAVES";
	      if (gb(w, x, y + 1, z).toString() == oleaves ||
	    	(gb(w, x, y + 1, z).toString() == sleaves) ||
	    	(gb(w, x, y + 1, z).toString() == bleaves) ||
	    	(gb(w, x, y + 1, z).toString() == jleaves) ||
	    	(gb(w, x, y + 1, z).toString() == aleaves) ||
	    	(gb(w, x, y + 1, z).toString() == doleaves)) {
	    	  breakChain(w, x, y + 1, z);
	      }
	      if (gb(w, x, y - 1, z).toString() == oleaves ||
	  	    (gb(w, x, y - 1, z).toString() == sleaves) ||
		   	(gb(w, x, y - 1, z).toString() == bleaves) ||
		   	(gb(w, x, y - 1, z).toString() == jleaves) ||
		   	(gb(w, x, y - 1, z).toString() == aleaves) ||
		   	(gb(w, x, y - 1, z).toString() == doleaves)) {
	    	  breakChain(w, x, y - 1, z);
	      }
	      if (gb(w, x + 1, y, z).toString() == oleaves ||
		  	 (gb(w, x + 1, y, z).toString() == sleaves) ||
		   	 (gb(w, x + 1, y, z).toString() == bleaves) ||
		  	 (gb(w, x + 1, y, z).toString() == jleaves) ||
			 (gb(w, x + 1, y, z).toString() == aleaves) ||
			 (gb(w, x + 1, y, z).toString() == doleaves)) {
	    	  breakChain(w, x + 1, y, z);
	      }
	      if (gb(w, x - 1, y, z).toString() == oleaves ||
	 		 (gb(w, x - 1, y, z).toString() == sleaves) ||
			 (gb(w, x - 1, y, z).toString() == bleaves) ||
			 (gb(w, x - 1, y, z).toString() == jleaves) ||
			 (gb(w, x - 1, y, z).toString() == aleaves) ||
			 (gb(w, x - 1, y, z).toString() == doleaves)) {
	    	  breakChain(w, x - 1, y, z);
	      }
	      if (gb(w, x - 1, y, z + 1).toString() == oleaves ||
	 	 	 (gb(w, x, y, z + 1).toString() == sleaves) ||
			 (gb(w, x, y, z + 1).toString() == bleaves) ||
			 (gb(w, x, y, z + 1).toString() == jleaves) ||
			 (gb(w, x, y, z + 1).toString() == aleaves) ||
			 (gb(w, x, y, z + 1).toString() == doleaves)) {
	    	  breakChain(w, x, y, z + 1);
	      }
	      if (gb(w, x - 1, y, z - 1).toString() == oleaves ||
	 	 	 (gb(w, x, y, z - 1).toString() == sleaves) ||
			 (gb(w, x, y, z - 1).toString() == bleaves) ||
			 (gb(w, x, y, z - 1).toString() == jleaves) ||
			 (gb(w, x, y, z - 1).toString() == aleaves) ||
			 (gb(w, x, y, z - 1).toString() == doleaves)) {
	    	  breakChain(w, x, y, z - 1); 
	      		} 
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
