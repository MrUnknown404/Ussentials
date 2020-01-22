package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandEnderChest extends CommandBase {
	
	@Override
	public String getName() {
		return "ec";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/ec";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP toOpen = null, pl = (EntityPlayerMP) sender;
		
		if (args.length == 0) {
			toOpen = (EntityPlayerMP) sender;
		} else if (args.length == 1) {
			toOpen = server.getPlayerList().getPlayerByUsername(args[0]);
			
			if (toOpen == null) {
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not find player!")));
				return;
			}
		}
		
		pl.displayGUIChest(toOpen.getInventoryEnderChest());
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
