package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.PowerToolHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandPowerTool extends CommandBase {
	
	@Override
	public String getName() {
		return "pt";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/pt [command (optional)] or /pt to remove the powertool (must hold an item)";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		if (player.getHeldItemMainhand().isEmpty() || player.getHeldItemMainhand() == null) {
			throw new CommandException(getUsage(sender));
		}
		
		if (args.length == 0) {
			PowerToolHelper.removePowerTool(((EntityPlayerMP) sender).getPersistentID(), player.getHeldItemMainhand().getItem());
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String str : args) {
			sb.append(str + " ");
		}
		
		PowerToolHelper.addPowerTool(((EntityPlayerMP) sender).getPersistentID(), player.getHeldItemMainhand().getItem(), sb.toString().trim());
	}
}
