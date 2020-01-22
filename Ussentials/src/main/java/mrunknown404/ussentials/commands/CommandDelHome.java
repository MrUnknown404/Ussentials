package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.HomeHandler;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandDelHome extends CommandBase {
	
	@Override
	public String getName() {
		return "delhome";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/delhome [name]";
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
		} else if (ModConfig.maxHomes == 0) {
			throw new CommandException("Homes are disabled");
		}
		
		HomeHandler.delHome((EntityPlayerMP) sender, args[0]);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, CommandHome.convert(sender)) : Collections.emptyList();
	}
}
