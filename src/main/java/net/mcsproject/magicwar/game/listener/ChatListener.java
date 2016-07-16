package net.mcsproject.magicwar.game.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class ChatListener implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setFormat(""); //TODO: Waiting for Ilou
	}

	@EventHandler
	public void onTabComplete(PlayerChatTabCompleteEvent e) {
		e.getTabCompletions().clear();
	}
}
