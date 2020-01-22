package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandHat extends CommandBase {
	
	@Override
	public String getName() {
		return "hat";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/hat (must hold an item)";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP pl = ((EntityPlayerMP) sender);
		if (pl.getHeldItemMainhand() == null || pl.getHeldItemMainhand().isEmpty()) {
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cMust hold an item!")));
			return;
		}
		
		ItemStack itemOnHead = pl.inventory.armorInventory.get(3);
		pl.inventory.armorInventory.set(3, pl.getHeldItem(EnumHand.MAIN_HAND));
		pl.setHeldItem(EnumHand.MAIN_HAND, itemOnHead);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
	
}
