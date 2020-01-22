package mrunknown404.ussentials.commands;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandBroadcast extends CommandBase {
	
	@Override
	public String getName() {
		return "broadcast";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/broadcast [text]";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			throw new CommandException(getUsage(sender));
		}
		
		StringBuilder sb = new StringBuilder();
		for (String str : args) {
			sb.append(str + " ");
		}
		
		server.getPlayerList().sendMessage(new TextComponentString(ColorUtils.addColor("&" + ModConfig.broadcastColorCode + "[Broadcast]&f " + sb.toString())));
	}
}
