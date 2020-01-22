package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.WarpHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandDelWarp extends CommandBase {
	
	@Override
	public String getName() {
		return "delwarp";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/delwarp [name]";
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		WarpHandler.delWarp((EntityPlayerMP) sender, args[0]);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, CommandWarp.convert(server, sender)) : Collections.emptyList();
	}
}
