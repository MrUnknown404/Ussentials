package mrunknown404.ussentials.commands;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandTop extends CommandBase {
	
	@Override
	public String getName() {
		return "top";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/top";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP pl = (EntityPlayerMP) sender;
		World w = pl.world;
		
		boolean foundCeiling = false;
		for (int i = pl.getPosition().getY(); i < 255; i++) {
			Block b = w.getBlockState(new BlockPos(pl.getPosition().getX(), i, pl.getPosition().getZ())).getBlock();
			
			if (!foundCeiling) {
				if (b != Blocks.AIR) {
					foundCeiling = true;
				}
			} else {
				if (b == Blocks.AIR) {
					pl.setPositionAndUpdate(pl.getPositionVector().x, i, pl.getPositionVector().z);
					return;
				}
			}
		}
		
		pl.sendMessage(new TextComponentString(ColorUtils.addColor("&cNothing is above you")));
	}
}
