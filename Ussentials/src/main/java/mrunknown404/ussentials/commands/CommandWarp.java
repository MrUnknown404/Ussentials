package mrunknown404.ussentials.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.LocationInfo;
import mrunknown404.ussentials.utils.WarpHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandWarp extends CommandBase {
	
	@Override
	public String getName() {
		return "warp";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/warp [name]";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP pl = ((EntityPlayerMP) sender);
		
		for (LocationInfo info : WarpHandler.getWarps()) {
			if (info.name.equalsIgnoreCase(args[0])) {
				if (pl.dimension != info.dimensionID) {
					pl.changeDimension(info.dimensionID);
				}
				
				pl.setPositionAndUpdate(info.pos.getX(), info.pos.getY(), info.pos.getZ());
				break;
			}
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, convert()) : Collections.emptyList();
	}
	
	public static List<String> convert() {
		List<String> strs = new ArrayList<String>();
		for (LocationInfo i : WarpHandler.getWarps()) {
			strs.add(i.name);
		}
		
		return strs;
	}
}
