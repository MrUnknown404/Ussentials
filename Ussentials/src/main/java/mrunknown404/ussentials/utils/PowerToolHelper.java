package mrunknown404.ussentials.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mrunknown404.ussentials.Main;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

public class PowerToolHelper {
private static final Map<UUID, Map<String, String>> POWERTOOLS = new HashMap<UUID, Map<String, String>>();
	
	public static void attemptPowerToolUse(EntityPlayerMP player) {
		Map<String, String> pt = getPlayersPowerTools(player.getPersistentID());
		
		if (pt.isEmpty()) {
			return;
		}
		
		if (pt.get(player.getHeldItemMainhand().getItem().getRegistryName().toString()) != null) {
			FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().executeCommand(player,
					pt.get(player.getHeldItemMainhand().getItem().getRegistryName().toString()));
		}
	}
	
	public static void addPowerTool(UUID player, Item item, String cmd) {
		Map<String, String> pt = getPlayersPowerTools(player);
		pt.put(item.getRegistryName().toString(), cmd);
		
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter fw = new FileWriter(new File(Loader.instance().getConfigDir(), Main.MOD_ID + "/" + player.toString() + "_powertools.cfg"));
			
			g.toJson(pt, fw);
			POWERTOOLS.put(player, pt);
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removePowerTool(UUID player, Item item) {
		Map<String, String> pt = getPlayersPowerTools(player);
		pt.remove(item.getRegistryName().toString());
		
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter fw = new FileWriter(new File(Loader.instance().getConfigDir(), Main.MOD_ID + "/" + player.toString() + "_powertools.cfg"));
			
			g.toJson(pt, fw);
			POWERTOOLS.put(player, pt);
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadPlayersPowerToolsFromFile(UUID player) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		try {
			File f = new File(Loader.instance().getConfigDir(), Main.MOD_ID + "/" + player.toString() + "_powertools.cfg");
			if (!f.exists()) {
				f.createNewFile();
			}
			
			FileReader fr = new FileReader(f);
			POWERTOOLS.put(player, g.fromJson(fr, new TypeToken<Map<String, String>>(){}.getType()));
			
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<UUID, Map<String, String>> getPowerTools() {
		return POWERTOOLS;
	}
	
	public static Map<String, String> getPlayersPowerTools(UUID player) {
		return POWERTOOLS.get(player) == null ? new HashMap<String, String>() : POWERTOOLS.get(player);
	}
}
