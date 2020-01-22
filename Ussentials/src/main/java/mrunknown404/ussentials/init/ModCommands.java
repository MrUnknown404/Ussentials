package mrunknown404.ussentials.init;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mrunknown404.ussentials.commands.CommandBroadcast;
import mrunknown404.ussentials.commands.CommandDelHome;
import mrunknown404.ussentials.commands.CommandDelWarp;
import mrunknown404.ussentials.commands.CommandEnch;
import mrunknown404.ussentials.commands.CommandEnderChest;
import mrunknown404.ussentials.commands.CommandFeed;
import mrunknown404.ussentials.commands.CommandFireball;
import mrunknown404.ussentials.commands.CommandHat;
import mrunknown404.ussentials.commands.CommandHeal;
import mrunknown404.ussentials.commands.CommandHome;
import mrunknown404.ussentials.commands.CommandHomes;
import mrunknown404.ussentials.commands.CommandJoin;
import mrunknown404.ussentials.commands.CommandLeave;
import mrunknown404.ussentials.commands.CommandMob;
import mrunknown404.ussentials.commands.CommandPowerTool;
import mrunknown404.ussentials.commands.CommandRelore;
import mrunknown404.ussentials.commands.CommandRename;
import mrunknown404.ussentials.commands.CommandSayAs;
import mrunknown404.ussentials.commands.CommandSetHome;
import mrunknown404.ussentials.commands.CommandSetWarp;
import mrunknown404.ussentials.commands.CommandSkull;
import mrunknown404.ussentials.commands.CommandSmite;
import mrunknown404.ussentials.commands.CommandSuicide;
import mrunknown404.ussentials.commands.CommandTop;
import mrunknown404.ussentials.commands.CommandTpAll;
import mrunknown404.ussentials.commands.CommandUssentials;
import mrunknown404.ussentials.commands.CommandWarp;
import mrunknown404.ussentials.commands.CommandWarps;
import net.minecraft.command.CommandBase;

public class ModCommands {
	public static final List<CommandBase> COMMANDS = new ArrayList<CommandBase>();
	
	public static final CommandBase USSENTIALS = addCommand(new CommandUssentials());
	public static final CommandBase SAY_AS = addCommand(new CommandSayAs());
	public static final CommandBase JOIN = addCommand(new CommandJoin());
	public static final CommandBase LEAVE = addCommand(new CommandLeave());
	public static final CommandBase BROADCAST = addCommand(new CommandBroadcast());
	public static final CommandBase TOP = addCommand(new CommandTop());
	public static final CommandBase TP_ALL = addCommand(new CommandTpAll());
	public static final CommandBase SMITE = addCommand(new CommandSmite());
	public static final CommandBase HEAL = addCommand(new CommandHeal());
	public static final CommandBase FEED = addCommand(new CommandFeed());
	public static final CommandBase FIREBALL = addCommand(new CommandFireball());
	public static final CommandBase ENCH = addCommand(new CommandEnch());
	public static final CommandBase MOB = addCommand(new CommandMob());
	public static final CommandBase POWERTOOL = addCommand(new CommandPowerTool());
	public static final CommandBase RENAME = addCommand(new CommandRename());
	public static final CommandBase SKULL = addCommand(new CommandSkull());
	public static final CommandBase HAT = addCommand(new CommandHat());
	public static final CommandBase SUICIDE = addCommand(new CommandSuicide());
	public static final CommandBase HOME = addCommand(new CommandHome());
	public static final CommandBase HOMES = addCommand(new CommandHomes());
	public static final CommandBase SET_HOME = addCommand(new CommandSetHome());
	public static final CommandBase DEL_HOME = addCommand(new CommandDelHome());
	public static final CommandBase RELORE = addCommand(new CommandRelore());
	public static final CommandBase WARP = addCommand(new CommandWarp());
	public static final CommandBase WARPS = addCommand(new CommandWarps());
	public static final CommandBase SET_WARP = addCommand(new CommandSetWarp());
	public static final CommandBase DEL_WARP = addCommand(new CommandDelWarp());
	public static final CommandBase ENDER_CHEST = addCommand(new CommandEnderChest());
	
	private static CommandBase addCommand(CommandBase cmd) {
		COMMANDS.add(cmd);
		COMMANDS.sort(Comparator.comparing(CommandBase::getName));
		return cmd;
	}
}
