package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTpAll extends CommandBase {
	
	@Override
	public String getName() {
		return "tpall";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/tpall";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP p = (EntityPlayerMP) sender;
		for (EntityPlayerMP pl : server.getPlayerList().getPlayers()) {
			if (pl != p) {
				pl.setPositionAndUpdate(p.getPositionVector().x, p.getPositionVector().y, p.getPositionVector().z);
			}
		}
	}
}
