package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ingame.BlockBreakListener;
import net.mcsproject.magicwar.game.listener.ingame.InteractListener;
import net.mcsproject.magicwar.game.listener.ingame.ItemDropListener;
import net.mcsproject.magicwar.game.listener.ingame.PlayerDeathListener;
import net.mcsproject.magicwar.utils.ChatUtils;
import net.mcsproject.magicwar.utils.ListenerBundle;
import org.bukkit.Bukkit;

public class WarmupCountdown extends Countdown {

	private ListenerBundle bundle;

	public WarmupCountdown() {
		super(300, false);
	}

	@Override
	public void sendMessage() {

	}

	@Override
	public void onInit() {
		this.bundle = new ListenerBundle(new PlayerDeathListener(), new InteractListener(), new ItemDropListener(), new BlockBreakListener());
		this.bundle.register();
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
		this.bundle.unregister();
		Bukkit.broadcastMessage(ChatUtils.fromConfig("peaceend"));
	}

}
