package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandSuicide extends CommandBase {
	
	@Override
	public String getName() {
		return "suicide";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/suicide";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		((EntityPlayerMP) sender).onKillCommand();
	}
}
