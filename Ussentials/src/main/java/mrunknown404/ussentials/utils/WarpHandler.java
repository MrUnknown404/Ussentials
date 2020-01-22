package mrunknown404.ussentials.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mrunknown404.unknownlibs.utils.ColorUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.DimensionManager;

public class WarpHandler {
	/** Don't get directly use {@link WarpHandler#getWarps()} */
	private static List<LocationInfo> warps = new ArrayList<LocationInfo>();
	
	public static void setWarp(EntityPlayerMP player, String name) {
		for (LocationInfo info : getWarps()) {
			if (info.name.equalsIgnoreCase(name)) {
				player.sendMessage(new TextComponentString(ColorUtils.addColor("&cA warp already exists with that name!")));
				return;
			}
		}
		
		warps.add(new LocationInfo(name, player));
		warps.sort(Comparator.comparing(LocationInfo::getName));
		
		player.sendMessage(new TextComponentString(ColorUtils.addColor("&aSuccessfully set warp : " + name)));
		saveToFile();
	}
	
	public static void delWarp(EntityPlayerMP player, String name) {
		LocationInfo iinfo = null;
		for (LocationInfo info : getWarps()) {
			if (info.name.equalsIgnoreCase(name)) {
				iinfo = info;
				break;
			}
		}
		
		if (iinfo != null) {
			warps.remove(iinfo);
			warps.sort(Comparator.comparing(LocationInfo::getName));
			
			System.out.println(warps);
			
			player.sendMessage(new TextComponentString(ColorUtils.addColor("&aSuccessfully deleted warp : " + name)));
			saveToFile();
		} else {
			player.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not find any warps with that name!")));
		}
	}
	
	public static List<LocationInfo> getWarps() {
		if (warps.isEmpty()) {
			readFromFile();
		}
		
		return warps;
	}
	
	public static void saveToFile() {
		Gson g = new GsonBuilder().create();
		System.out.println("Saving to file: " + DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsWarpData.dat");
		
		try {
			FileWriter fw = new FileWriter(DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsWarpData.dat");
			g.toJson(warps, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readFromFile() {
		Gson g = new GsonBuilder().create();
		System.out.println("Reading from file: " + DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsWarpData.dat");
		
		File f = new File(DimensionManager.getCurrentSaveRootDirectory() + "/data/UssentialsWarpData.dat");
		if (!f.exists()) {
			saveToFile();
		}
		
		try {
			FileReader fr = new FileReader(f);
			warps = g.fromJson(fr, new TypeToken<List<LocationInfo>>() {
			}.getType());
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (warps == null) {
			warps = new ArrayList<LocationInfo>();
			saveToFile();
		}
	}
}
