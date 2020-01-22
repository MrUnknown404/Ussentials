package mrunknown404.ussentials.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mrunknown404.unknownlibs.utils.MathUtils;
import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommandMob extends CommandBase {
	
	@Override
	public String getName() {
		return "mob";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/mob [mob] [amount (optional)]";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		if (args.length == 1 || args.length == 2) {
			if (ForgeRegistries.ENTITIES.getValue(new ResourceLocation(args[0])) == null) {
				throw new CommandException(getUsage(sender));
			}
			
			int amount = 1;
			if (args.length == 2) {
				try {
					amount = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					throw new CommandException(getUsage(sender));
				}
			}
			
			RayTraceResult ray = MathUtils.rayTrace((EntityPlayerMP) sender, 64, 0, false);
			if (ray != null && ray.typeOfHit == Type.BLOCK) {
				for (int i = 0; i < amount; i++) {
					Entity entity = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(args[0])).newInstance(server.getEntityWorld()); //fix
					entity.setPosition(ray.hitVec.x, ray.hitVec.y, ray.hitVec.z);
					server.getEntityWorld().spawnEntity(entity);
				}
			}
		} else {
			throw new CommandException(getUsage(sender));
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, EntityList.getEntityNameList()) : args.length == 2 ? Arrays.asList("#") : Collections.emptyList();
	}
}
