package mrunknown404.ussentials.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.unknownlibs.utils.MathUtils;
import mrunknown404.ussentials.Main;
import mrunknown404.ussentials.utils.HomeHandler;
import mrunknown404.ussentials.utils.LocationInfo;
import mrunknown404.ussentials.utils.ModConfig;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandHomes extends CommandBase {
	
	@Override
	public String getName() {
		return "homes";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/homes";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length >= 2) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		} else if (ModConfig.maxHomes == 0) {
			throw new CommandException("Homes are disabled");
		}
		
		EntityPlayerMP pl = (EntityPlayerMP) sender;
		if (HomeHandler.getPlayerHomes(pl.getGameProfile().getId()).isEmpty()) {
			throw new CommandException("You don't have any homes!");
		}
		
		List<LocationInfo> homes = new ArrayList<LocationInfo>();
		for (LocationInfo entry : HomeHandler.getPlayerHomes(pl.getGameProfile().getId())) {
			homes.add(entry);
		}
		
		homes.sort(Comparator.comparing(LocationInfo::getName));
		
		if (args.length == 0) {
			sender.sendMessage(new TextComponentString(""));
			for (int i = 0; i < Math.min(ModConfig.homesPrintLength, homes.size()); i++) {
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&eHome: " + homes.get(i).name)));
			}
			
			sender.sendMessage(new TextComponentString(ColorUtils.addColor("&aPage 1/" + (1 + MathUtils.floor(homes.size() / ModConfig.homesPrintLength)))));
		} else if (args.length == 1) {
			try {
				int page = Integer.parseInt(args[0]) - 1;
				if (page < 0 || page > MathUtils.floor(homes.size() / ModConfig.homesPrintLength)) {
					throw new CommandException(getUsage(sender));
				}
				
				sender.sendMessage(new TextComponentString(""));
				for (int i = ModConfig.homesPrintLength * page; i < Math.min((ModConfig.homesPrintLength * page) + ModConfig.homesPrintLength, homes.size()); i++) {
					sender.sendMessage(new TextComponentString(ColorUtils.addColor("&eHome: " + homes.get(i).name)));
				}
				
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&aPage " + args[0] + "/" + (1 + MathUtils.floor(homes.size() / ModConfig.homesPrintLength)))));
			} catch (NumberFormatException e) {
				throw new CommandException(getUsage(sender));
			}
		}
	}
}
