package postevents.common;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void init() {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}

	public boolean isServer() {
		return true;
	}

	public boolean isClient() {
		return false;
	}

}
