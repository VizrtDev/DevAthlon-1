package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ChatListener;
import net.mcsproject.magicwar.game.listener.JoinLeaveListener;
import net.mcsproject.magicwar.game.listener.lobby.BlockBreakListener;
import net.mcsproject.magicwar.game.listener.lobby.EntityDamageListener;
import net.mcsproject.magicwar.game.listener.lobby.WeatherChangeListener;
import net.mcsproject.magicwar.utils.ListenerBundle;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Random;

public class LobbyCountdown extends Countdown {

	private ListenerBundle bundle;
	private World magicalWorld;
	private Random random = new Random();

	public LobbyCountdown() {
		super(60, true);
	}

	@Override
	public void sendMessage(Player p) {
		if (getTime() % 20 == 0 || getTime() <= 10) {
			p.sendMessage("§7» §aDas Spiel startet in " + getTime() + " Sekunden!");
		}
	}

	@Override
	public void onTick() {
		if (this.getTime() == 15) {
			WorldCreator wc = new WorldCreator("MagicalWorld");
			wc.environment(World.Environment.NORMAL);
			wc.generateStructures(false);
			wc.seed(); //TODO: Own generator
			magicalWorld = Bukkit.createWorld(wc);
			magicalWorld.setPVP(true);
			magicalWorld.setDifficulty(Difficulty.EASY);
			magicalWorld.setTime(14000);
			magicalWorld.setGameRuleValue("doDaylightCycle", "false");
			magicalWorld.setAutoSave(false);
			magicalWorld.getWorldBorder().setSize(MagicWar.getInstance().getConfig().getInt("worldbordersize"));
		}
	}

	@Override
	public void onInit() {
		this.bundle = new ListenerBundle(
				new JoinLeaveListener(),
				new ChatListener(),
				new BlockBreakListener(),
				new EntityDamageListener(),
				new WeatherChangeListener()
		);

		this.bundle.register();
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onEnd() {
		Bukkit.getOnlinePlayers().forEach(this::randomTeleport);
		this.bundle.unregister();
	}

	private void randomTeleport(Player p) {
		Location location = magicalWorld.getSpawnLocation().clone();
		location.add(random.nextInt(6) - 3, 0, random.nextInt(6) - 3);
		location.setY(magicalWorld.getHighestBlockYAt(location.getBlockX(), location.getBlockZ()));
		p.teleport(location);
	}


}
