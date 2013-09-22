package io.rampant.postevents;

import com.google.inject.Singleton;
import models.postevents.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

@Singleton
public class PlayerStatus implements PostEventListener {
	private ConcurrentSkipListSet<String> players = new ConcurrentSkipListSet<>();

	public List<String> getPlayers() {
		return new ArrayList<>(players);
	}

	@Override
	public void onEvent(Event event) {
		if( "join".equalsIgnoreCase(event.event) ) {
			playerJoin(event.data.get("player"));
		}
		if( "part".equalsIgnoreCase(event.event) ) {
			playerPart(event.data.get("player"));
		}
	}

	private void playerJoin(String playerName) {
		players.add(playerName);
	}

	private void playerPart(String playerName) {
		players.remove(playerName);
	}

	private static class PlayerStatusHolder {
		public static final PlayerStatus INSTANCE = new PlayerStatus();
	}

	public static PlayerStatus getInstance() {
		return PlayerStatusHolder.INSTANCE;
	}
}
