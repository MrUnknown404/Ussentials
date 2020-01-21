package mrunknown404.ussentials.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class CommandEnch extends CommandBase {
	
	@Override
	public String getName() {
		return "ench";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/ench [enchantment] [level] (must hold an item)";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 2) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		Enchantment ench = Enchantment.getEnchantmentByLocation(args[0]);
		
		if (ench == null) {
			throw new CommandException(getUsage(sender));
		}
		
		int level;
		try {
			level = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			throw new CommandException(getUsage(sender));
		}
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		if (player.getHeldItemMainhand().isEmpty() || player.getHeldItemMainhand() == null) {
			throw new CommandException(getUsage(sender));
		}
		
		ItemStack item = player.getHeldItemMainhand();
		item.addEnchantment(ench, level);
		player.setHeldItem(EnumHand.MAIN_HAND, item);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, Enchantment.REGISTRY.getKeys()) : args.length == 2 ? Arrays.asList("#") : Collections.emptyList();
	}
}
