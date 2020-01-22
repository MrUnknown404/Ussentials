package mrunknown404.ussentials.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class LocationInfo implements Comparable<LocationInfo> {
	public final String name;
	public final int dimensionID;
	public final BlockPos pos;
	
	public LocationInfo(String name, EntityPlayerMP player) {
		this.name = name;
		this.dimensionID = player.dimension;
		this.pos = player.getPosition();
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(LocationInfo o) {
		return name.compareTo(o.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (this == obj) {
			return true;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		
		LocationInfo other = (LocationInfo) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
