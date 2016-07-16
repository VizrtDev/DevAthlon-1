package net.mcsproject.magicwar.game.commands;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.GamePhase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForceStartCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		MagicWar.getInstance().setGamePhase(GamePhase.PREGAME);
		sender.sendMessage("Die Spielephase wurde auf Pregame gesetzt!");
		return true;
	}
}
