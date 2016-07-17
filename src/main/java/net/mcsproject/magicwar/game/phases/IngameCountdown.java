package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ingame.BlockBreakListener;
import net.mcsproject.magicwar.game.listener.ingame.InteractListener;
import net.mcsproject.magicwar.game.listener.ingame.ItemDropListener;
import net.mcsproject.magicwar.game.listener.ingame.PlayerDeathListener;
import net.mcsproject.magicwar.utils.ListenerBundle;
import org.bukkit.entity.Player;

public class IngameCountdown extends Countdown {

	private ListenerBundle bundle;

	public IngameCountdown() {
		super(720, false);
	}

	@Override
	public void sendMessage(Player p) {
		if (getTime() % 60 == 0) {
			int minutes = getTime() / 60;
			p.sendMessage("§7» §cDas Deathmatch beginnt in " + minutes + " Minute" + (minutes != 1 ? "n" : ""));
		}
	}

	@Override
	public void onTick() {
	}

	@Override
	public void onInit() {
		this.bundle = new ListenerBundle(new BlockBreakListener(), new InteractListener(),
				new ItemDropListener(), new PlayerDeathListener());
		this.bundle.register();
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
		this.bundle.unregister();
	}

}
