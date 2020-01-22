package mrunknown404.ussentials.utils;

import mrunknown404.unknownlibs.utils.ColorUtils;
import mrunknown404.ussentials.Main;
import net.minecraftforge.common.config.Config;

@Config(modid = Main.MOD_ID)
public class ModConfig {
	@Config.Comment("The color code that '/broadcast' will use")
	public static ColorUtils.ColorCodes broadcastColorCode = ColorUtils.ColorCodes.DARK_RED;
	
	@Config.Comment("The max amount of commands that '/ussentials help' prints")
	@Config.RangeInt(min = 3, max = 32)
	public static int ussentialsHelpPrintLength = 7;
	
	@Config.Comment("The max amount of homes a player can have (0 will disable homes)")
	@Config.RangeInt(min = 0, max = 100)
	public static int maxHomes = 5;
	
	@Config.Comment("The max amount of commands that '/ussentials help' prints")
	@Config.RangeInt(min = 3, max = 32)
	public static int homesPrintLength = 7;
	
	@Config.Comment("The max amount of warps that '/warps' prints")
	@Config.RangeInt(min = 3, max = 32)
	public static int warpsPrintLength = 7;
}
