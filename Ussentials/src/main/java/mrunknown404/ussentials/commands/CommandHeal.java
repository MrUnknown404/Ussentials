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

public class CommandHeal extends CommandBase {
	
	@Override
	public String getName() {
		return "heal";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/heal [name (optional)]";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			throw new CommandException(getUsage(sender));
		}
		
		if (!(sender instanceof EntityPlayerMP) && args.length == 0) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP toHeal;
		if (args.length == 1) {
			toHeal = server.getPlayerList().getPlayerByUsername(args[0]);
		} else {
			toHeal = (EntityPlayerMP) sender;
			
			if (toHeal == null) {
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not find player!")));
				return;
			}
		}
		
		toHeal.heal(toHeal.getMaxHealth());
		sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou have healed " + toHeal.getDisplayNameString() + "!")));
		toHeal.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou have been healed!")));
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
