package net.mcsproject.magicwar.game.listener;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.GamePhase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (MagicWar.getInstance().getGamePhase() != GamePhase.LOBBY) {
			e.setKickMessage("Das Spiel hat leider schon begonnen!");
			e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
		}
	}
}
