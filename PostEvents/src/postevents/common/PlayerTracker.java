package postevents.common;

import cpw.mods.fml.common.IPlayerTracker;
import postevents.POSTEvents;
import postevents.Dispatcher;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;

public class PlayerTracker implements IPlayerTracker {
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if( POSTEvents.Config.event_JOIN && POSTEvents.proxy.isServer() ) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("player", player.getEntityName());
			Dispatcher.trigger(Dispatcher.TYPE.JOIN, data);
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		if( POSTEvents.Config.event_PART && POSTEvents.proxy.isServer() ) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("player", player.getEntityName());
			Dispatcher.trigger(Dispatcher.TYPE.PART, data);
		}
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}
}
