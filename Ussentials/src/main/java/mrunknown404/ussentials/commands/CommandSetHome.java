package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.HomeHandler;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandSetHome extends CommandBase {
	
	@Override
	public String getName() {
		return "sethome";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/sethome [name]";
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
		
		HomeHandler.setHome((EntityPlayerMP) sender, args[0]);
	}
}
