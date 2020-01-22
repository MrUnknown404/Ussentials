package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandFeed extends CommandBase {
	
	@Override
	public String getName() {
		return "feed";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/feed [name (optional)]";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			throw new CommandException(getUsage(sender));
		}
		
		if (!(sender instanceof EntityPlayerMP) && args.length == 0) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP toFeed;
		if (args.length == 1) {
			toFeed = server.getPlayerList().getPlayerByUsername(args[0]);
		} else {
			toFeed = (EntityPlayerMP) sender;
			
			if (toFeed == null) {
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not find player!")));
				return;
			}
		}
		
		
		toFeed.getFoodStats().setFoodLevel(20);
		sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou have fed " + toFeed.getDisplayNameString() + "!")));
		toFeed.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou have been fed!")));
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
