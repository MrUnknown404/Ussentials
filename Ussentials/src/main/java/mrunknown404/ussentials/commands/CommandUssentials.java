package mrunknown404.ussentials.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.unknownlibs.utils.MathUtils;
import mrunknown404.ussentials.init.ModCommands;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandUssentials extends CommandBase {
	
	private static final String[] SUB_COMMANDS = new String[] { "help" };
	
	@Override
	public String getName() {
		return "ussentials";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < SUB_COMMANDS.length; i++) {
			sb.append(SUB_COMMANDS[i]);
			if (i != SUB_COMMANDS.length - 1) {
				sb.append(", ");
			}
		}
		
		return "/ussentials [" + sb.toString() + "]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase(SUB_COMMANDS[0])) {
				sender.sendMessage(new TextComponentString(""));
				for (int i = 0; i < Math.min(ModConfig.ussentialsHelpPrintLength, ModCommands.COMMANDS.size()); i++) {
					sender.sendMessage(
							new TextComponentString(ColorUtils.addColor("&eCommand: " + ModCommands.COMMANDS.get(i).getName() + " : " + ModCommands.COMMANDS.get(i).getUsage(sender))));
				}
				
				sender.sendMessage(
						new TextComponentString(ColorUtils.addColor("&aPage 1/" + (1 + MathUtils.floor(ModCommands.COMMANDS.size() / ModConfig.ussentialsHelpPrintLength)))));
			} else {
				throw new CommandException(getUsage(sender));
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase(SUB_COMMANDS[0])) {
				try {
					int page = Integer.parseInt(args[1]) - 1;
					if (page < 0 || page > MathUtils.floor(ModCommands.COMMANDS.size() / ModConfig.ussentialsHelpPrintLength)) {
						throw new CommandException(getUsage(sender));
					}
					
					sender.sendMessage(new TextComponentString(""));
					for (int i = ModConfig.ussentialsHelpPrintLength * page; i < Math.min((ModConfig.ussentialsHelpPrintLength * page) + ModConfig.ussentialsHelpPrintLength,
							ModCommands.COMMANDS.size()); i++) {
						sender.sendMessage(new TextComponentString(
								ColorUtils.addColor("&eCommand: " + ModCommands.COMMANDS.get(i).getName() + " : " + ModCommands.COMMANDS.get(i).getUsage(sender))));
					}
					
					sender.sendMessage(new TextComponentString(
							ColorUtils.addColor("&aPage " + args[1] + "/" + (1 + MathUtils.floor(ModCommands.COMMANDS.size() / ModConfig.ussentialsHelpPrintLength)))));
				} catch (NumberFormatException e) {
					throw new CommandException(getUsage(sender));
				}
			} else {
				throw new CommandException(getUsage(sender));
			}
		} else {
			throw new CommandException(getUsage(sender));
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, SUB_COMMANDS) : args.length == 2 ? Arrays.asList("#") : Collections.emptyList();
	}
}
