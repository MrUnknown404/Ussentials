package mrunknown404.ussentials.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class HomeInfo implements Comparable<HomeInfo> {
	public final String name;
	public final int dimensionID;
	public final BlockPos pos;
	
	public HomeInfo(String name, EntityPlayerMP player) {
		this.name = name;
		this.dimensionID = player.dimension;
		this.pos = player.getPosition();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(HomeInfo o) {
		return name.compareTo(o.name);
	}
}
