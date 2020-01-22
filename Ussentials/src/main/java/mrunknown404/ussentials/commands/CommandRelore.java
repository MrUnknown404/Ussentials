package mrunknown404.ussentials.commands;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.unknownlibs.utils.NBTUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

public class CommandRelore extends CommandBase {
	
	@Override
	public String getName() {
		return "relore";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/relore [lore] (must hold an item) (can do \\n for a new line)";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cMust type a new lore!")));
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
		
		player.setHeldItem(EnumHand.MAIN_HAND, NBTUtils.addLore(player.getHeldItemMainhand(), ColorUtils.addColor(sb.toString()).split("\\\\n")));
	}
}
