package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.WarpHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandSetWarp extends CommandBase {
	
	@Override
	public String getName() {
		return "setwarp";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/setwarp [name]";
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		WarpHandler.setWarp((EntityPlayerMP) sender, args[0]);
	}
}
