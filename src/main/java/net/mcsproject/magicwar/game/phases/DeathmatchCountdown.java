package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.utils.ChatUtils;
import org.bukkit.Bukkit;

public class DeathmatchCountdown extends Countdown {

	public DeathmatchCountdown() {
		super(120, false);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onInit() {

	}

	@Override
	public void onStart() {
		Bukkit.broadcastMessage(ChatUtils.fromConfig("deathmatchstart"));
	}

	@Override
	public void onEnd() {

	}

}
