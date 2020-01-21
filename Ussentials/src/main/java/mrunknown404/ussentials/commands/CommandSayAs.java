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

public class CommandSayAs extends CommandBase {
	
	@Override
	public String getName() {
		return "sayas";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/sayas [name] [message]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			String msg = "";
			
			for (int i = 1; i < args.length; i++) {
				msg += args[i] + " ";
			}
			
			server.getPlayerList().sendMessage(new TextComponentString(ColorUtils.addColor("<" + args[0] + "&f> " + msg)));
		} else {
			throw new CommandException(getUsage(sender));
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
