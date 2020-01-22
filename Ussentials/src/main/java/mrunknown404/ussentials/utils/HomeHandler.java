package mrunknown404.ussentials.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mrunknown404.unknownlibs.utils.ColorUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.DimensionManager;

public class HomeHandler {
	/** Don't get directly use {@link HomeHandler#getHomes()} */
	private static Map<UUID, List<LocationInfo>> homes = new HashMap<UUID, List<LocationInfo>>();
	
	public static void setHome(EntityPlayerMP player, String name) {
		if (doesPlayerHaveAvailableHomes(player.getGameProfile().getId())) {
			for (LocationInfo info : getHomes().get(player.getGameProfile().getId())) {
				if (info.name.equalsIgnoreCase(name)) {
					player.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou already have a home with that name!")));
					return;
				}
			}
			
			List<LocationInfo> l = getHomes().get(player.getGameProfile().getId());
			l.add(new LocationInfo(name, player));
			getHomes().put(player.getGameProfile().getId(), l);
			
			player.sendMessage(new TextComponentString(ColorUtils.addColor("&aSuccessfully set home : " + name)));
			saveToFile();
		} else {
			player.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not set home because you have too many!")));
		}
	}
	
	public static void delHome(EntityPlayerMP player, String name) {
		if (getHomes().containsKey(player.getGameProfile().getId())) {
			LocationInfo iinfo = null;
			for (LocationInfo info : getPlayerHomes(player.getGameProfile().getId())) {
				if (info.name.equalsIgnoreCase(name)) {
					iinfo = info;
					break;
				}
			}
			
			if (iinfo != null) {
				getPlayerHomes(player.getGameProfile().getId()).remove(iinfo);
				player.sendMessage(new TextComponentString(ColorUtils.addColor("&aSuccessfully deleted home : " + name)));
				saveToFile();
			} else {
				player.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou do not have home with that name!")));
			}
		}
	}
	
	private static boolean doesPlayerHaveAvailableHomes(UUID player) {
		return getPlayerHomes(player).size() <= ModConfig.maxHomes;
	}
	
	public static List<LocationInfo> getPlayerHomes(UUID player) {
		if (!getHomes().containsKey(player)) {
			getHomes().put(player, new ArrayList<LocationInfo>());
			saveToFile();
		}
		
		return getHomes().get(player);
	}
	
	private static Map<UUID, List<LocationInfo>> getHomes() {
		if (homes.isEmpty()) {
			readFromFile();
		}
		
		return homes;
	}
	
	public static void saveToFile() {
		Gson g = new GsonBuilder().create();
		System.out.println("Saving to file: " + DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsHomeData.dat");
		
		try {
			FileWriter fw = new FileWriter(DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsHomeData.dat");
			g.toJson(homes, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readFromFile() {
		Gson g = new GsonBuilder().create();
		System.out.println("Reading from file: " + DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsHomeData.dat");
		
		File f = new File(DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsHomeData.dat");
		if (!f.exists()) {
			saveToFile();
		}
		
		try {
			FileReader fr = new FileReader(f);
			homes = g.fromJson(fr, new TypeToken<Map<UUID, List<LocationInfo>>>() {}.getType());
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (homes == null) {
			homes = new HashMap<UUID, List<LocationInfo>>();
			saveToFile();
		}
	}
}
