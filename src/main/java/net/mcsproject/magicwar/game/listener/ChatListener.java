package net.mcsproject.magicwar.game.listener;

import net.mcsproject.magicwar.MagicWar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class ChatListener implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setFormat(MagicWar.getInstance().getConfig().getString("messages.chatformat")); //TODO: Waiting for Ilou
	}

	@EventHandler
	public void onTabComplete(PlayerChatTabCompleteEvent e) {
		e.getTabCompletions().clear();
	}
}
