package mrunknown404.ussentials.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.HomeHandler;
import mrunknown404.ussentials.utils.HomeInfo;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandHome extends CommandBase {
	
	@Override
	public String getName() {
		return "home";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/home [name]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		} else if (ModConfig.maxHomes == 0) {
			throw new CommandException("Homes are disabled");
		}
		
		EntityPlayerMP pl = ((EntityPlayerMP) sender);
		
		for (HomeInfo info : HomeHandler.getPlayerHomes(pl.getGameProfile().getId())) {
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
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, convert(server, sender)) : Collections.emptyList();
	}
	
	public static List<String> convert(MinecraftServer server, ICommandSender sender) {
		List<String> strs = new ArrayList<String>();
		for (HomeInfo i : HomeHandler.getPlayerHomes(((EntityPlayerMP) sender).getGameProfile().getId())) {
			strs.add(i.name);
		}
		
		return strs;
	}
}
