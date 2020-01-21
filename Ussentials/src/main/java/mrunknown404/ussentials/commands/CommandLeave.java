package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandLeave extends CommandBase {

	@Override
	public String getName() {
		return "leave";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/leave [name (optional)]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		String name = sender.getName();
		if (args.length == 1) {
			name = args[0];
		} else if (args.length > 1) {
			throw new CommandException(getUsage(sender));
		}
		
		server.getPlayerList().sendMessage(new TextComponentString(ColorUtils.addColor("&e" + name + " left the game")));
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
