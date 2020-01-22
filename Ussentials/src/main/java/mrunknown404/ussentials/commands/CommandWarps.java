package mrunknown404.ussentials.commands;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.unknownlibs.utils.MathUtils;
import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.ModConfig;
import mrunknown404.ussentials.utils.WarpHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandWarps extends CommandBase {
	
	@Override
	public String getName() {
		return "warps";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/warps";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		if (WarpHandler.getWarps().isEmpty()) {
			throw new CommandException("There aren't any warps!");
		}
		
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString(""));
			for (int i = 0; i < Math.min(ModConfig.warpsPrintLength, WarpHandler.getWarps().size()); i++) {
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&eWarp: " + WarpHandler.getWarps().get(i).name)));
			}
			
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&aPage 1/" + (1 + MathUtils.floor(WarpHandler.getWarps().size() / ModConfig.warpsPrintLength)))));
		} else if (args.length == 1) {
			try {
				int page = Integer.parseInt(args[0]) - 1;
				if (page < 0 || page > MathUtils.floor(WarpHandler.getWarps().size() / ModConfig.warpsPrintLength)) {
					throw new CommandException(getUsage(sender));
				}
				
				sender.sendMessage(new TextComponentString(""));
				for (int i = ModConfig.warpsPrintLength * page; i < Math.min((ModConfig.warpsPrintLength * page) + ModConfig.warpsPrintLength, WarpHandler.getWarps().size()); i++) {
					sender.sendMessage(new TextComponentString(ColorUtils.addColor("&eWarp: " + WarpHandler.getWarps().get(i).name)));
				}
				
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&aPage " + args[0] + "/" + (1 + MathUtils.floor(WarpHandler.getWarps().size() / ModConfig.warpsPrintLength)))));
			} catch (NumberFormatException e) {
				throw new CommandException(getUsage(sender));
			}
		}
	}
}
