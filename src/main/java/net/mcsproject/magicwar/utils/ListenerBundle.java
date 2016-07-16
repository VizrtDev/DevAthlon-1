package net.mcsproject.magicwar.utils;

import net.mcsproject.magicwar.MagicWar;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListenerBundle {

	private List<Listener> listeners = new ArrayList<>();

	public ListenerBundle(Listener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	public void register() {
		this.listeners.forEach(l -> Bukkit.getPluginManager().registerEvents(l, MagicWar.getInstance()));
	}

	public void unregister() {
		this.listeners.forEach(HandlerList::unregisterAll);
	}

}
