package mrunknown404.ussentials;

import mrunknown404.ussentials.init.ModCommands;
import net.minecraft.command.CommandBase;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MOD_ID, serverSideOnly = true, useMetadata = true, dependencies = "required-after:unknownlibs@1.0.1", acceptableRemoteVersions = "*")
public class Main {
	public static final String MOD_ID = "ussentials", NOT_PLAYER_ERROR = "That command can only be used like that as a player";
	
	@Instance
	public static Main main;
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent e) {
		for (CommandBase cmd : ModCommands.COMMANDS) {
			e.registerServerCommand(cmd);
		}
	}
}
