package net.mcsproject.magicwar.utils;

import net.mcsproject.magicwar.MagicWar;
import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
	public static String fromConfig(String message) {
		return ChatColor.translateAlternateColorCodes('&', MagicWar.getInstance().getConfig().getString("messages.prefix") + MagicWar.getInstance().getConfig().getString("messages." + message));
	}
}
