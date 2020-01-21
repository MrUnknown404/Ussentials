package mrunknown404.ussentials.commands;

import mrunknown404.ussentials.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.ForgeEventFactory;

public class CommandFireball extends CommandBase {
	
	@Override
	public String getName() {
		return "fireball";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "/fireball";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 3;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 0) {
			throw new CommandException(getUsage(sender));
		} else if (!(sender instanceof EntityPlayerMP)) {
			throw new CommandException(Main.NOT_PLAYER_ERROR);
		}
		
		EntityPlayerMP p = (EntityPlayerMP) sender;
		
		double yaw = Math.toRadians(p.rotationYaw);
		double pitch = Math.toRadians(p.rotationPitch);
		double xDirection = -Math.sin(yaw) * Math.cos(pitch);
		double yDirection = -Math.sin(pitch);
		double zDirection = Math.cos(yaw) * Math.cos(pitch);
		double x = p.posX + (xDirection * 1.5);
		double y = p.posY + 1.5 + (yDirection * 2);
		double z = p.posZ + (zDirection * 1.5);
		
		EntityFireball fireball = new EntityFireball(server.getEntityWorld(), x, y, z, xDirection, yDirection, zDirection) {
			@Override
			protected void onImpact(RayTraceResult r) {
				if (!world.isRemote) {
					if (r.entityHit != null) {
						r.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, shootingEntity), 6.0F);
						applyEnchantments(shootingEntity, r.entityHit);
					}
					
					boolean flag = ForgeEventFactory.getMobGriefingEvent(world, shootingEntity);
					Explosion explosion = new Explosion(world, null, x, y, z, 4f, flag, flag);
					explosion.doExplosionB(true);
					setDead();
				}
			}
		};
		
		server.getEntityWorld().spawnEntity(fireball);
	}
}
