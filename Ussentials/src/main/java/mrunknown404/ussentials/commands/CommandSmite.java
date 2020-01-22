package mrunknown404.ussentials.commands;

import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.unknownlibs.utils.MathUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

public class CommandSmite extends CommandBase {
	
	@Override
	public String getName() {
		return "smite";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/smite [name (optional)]";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0 || args.length == 1) {
			if (args.length == 0 && !(sender instanceof EntityPlayerMP)) {
				throw new CommandException(Main.NOT_PLAYER_ERROR);
			}
			
			Vec3d where = null;
			if (args.length == 1) {
				EntityPlayerMP toSmite = server.getPlayerList().getPlayerByUsername(args[0]);
				if (toSmite == null) {
					sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cCould not find player!")));
					return;
				}
				
				where = toSmite.getPositionVector();
				
				toSmite.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou were smitten!")));
				sender.sendMessage(new TextComponentString(ColorUtils.addColor("&cYou have smitten " + args[0] + "!")));
			} else {
				RayTraceResult ray = MathUtils.rayTrace((EntityPlayerMP) sender, 64, 0, true);
				if (ray != null && ray.typeOfHit == Type.BLOCK) {
					where = ray.hitVec;
				}
			}
			
			if (where == null) {
				throw new CommandException(getUsage(sender));
			}
			
			server.getEntityWorld().addWeatherEffect(new EntityLightningBolt(server.getEntityWorld(), where.x, where.y, where.z, false));
		} else {
			throw new CommandException(getUsage(sender));
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
	}
}
