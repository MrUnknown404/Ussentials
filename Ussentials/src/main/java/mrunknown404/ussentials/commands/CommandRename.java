package mrunknown404.ussentials.commands;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

public class CommandRename extends CommandBase {
	
	@Override
	public String getName() {
		return "rename";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/rename [name] (must hold an item)";
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
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cMust type a new name!")));
			return;
		} else if (player.getHeldItemMainhand() == null || player.getHeldItemMainhand().isEmpty()) {
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cMust hold an item!")));
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			
			if (i != args.length - 1) {
				sb.append(" ");
			}
		}
		
		ItemStack item = player.getHeldItemMainhand();
		item.setStackDisplayName(ColorUtils.addColor(sb.toString()));
		player.setHeldItem(EnumHand.MAIN_HAND, item);
	}
	
}
