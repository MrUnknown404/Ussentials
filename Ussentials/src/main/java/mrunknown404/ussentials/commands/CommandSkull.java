package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandSkull extends CommandBase {
	
	@Override
	public String getName() {
		return "skull";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/skull [name (optional)]";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		String owner = args.length == 0 ? sender.getName() : args[0];
		if (args.length >= 2) {
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cToo many arguments")));
			return;
		}
		
		NBTTagCompound nbt = new NBTTagCompound();
		ItemStack item = new ItemStack(Items.SKULL, 1, 3);
		nbt.setTag("SkullOwner", new NBTTagString(owner));
		item.setTagCompound(nbt);
		item.setStackDisplayName(ColorUtils.addColor("&6Head of " + owner));
		((EntityPlayerMP) sender).setHeldItem(EnumHand.MAIN_HAND, item);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
