package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DeathmatchCountdown extends Countdown {

	public DeathmatchCountdown() {
		super(120, false);
	}

	@Override
	public void sendMessage(Player p) {
		if (getTime() % 60 == 0 || getTime() <= 10) {
			p.sendMessage("§7» §cDas Deathmatch endet in " + getTime() + " Sekunden");
		}
	}

	@Override
	public void onTick() {

	}

	@Override
	public void onInit() {

	}

	@Override
	public void onStart() {
		Bukkit.broadcastMessage(ChatUtils.fromConfig("deathmatchstart"));
		Bukkit.getOnlinePlayers().forEach(p -> p.teleport(p.getWorld().getSpawnLocation()));
	}

	@Override
	public void onEnd() {

	}

}
